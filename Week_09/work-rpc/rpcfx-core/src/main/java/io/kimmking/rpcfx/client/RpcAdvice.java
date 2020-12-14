package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

import static io.kimmking.rpcfx.client.RpcEnhance.URL_MAP;

public final class RpcAdvice {

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    public static final MediaType JSON_TYPE = MediaType.get("application/json; charset=utf-8");

    @RuntimeType
    public Object proxyInvoke(@Origin Method method, @AllArguments Object[] params) {
        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(method.getDeclaringClass().getName());
        request.setMethod(method.getName());
        request.setParams(params);

        RpcfxResponse response = null;
        try {
            response = post(request, URL_MAP.get(method.getDeclaringClass()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 这里判断response.status，处理异常
        // 考虑封装一个全局的RpcfxException

       return JSON.parse(Objects.requireNonNull(response).getResult().toString());
    }

    private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: "+reqJson);

        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSON_TYPE, reqJson))
                .build();
        String respJson = Objects.requireNonNull(client.newCall(request).execute().body()).string();
        System.out.println("resp json: "+respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }
}
