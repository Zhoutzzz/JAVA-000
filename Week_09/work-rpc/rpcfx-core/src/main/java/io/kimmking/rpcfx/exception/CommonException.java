package io.kimmking.rpcfx.exception;

import io.kimmking.rpcfx.api.RpcfxResponse;

public class CommonException {

    public static RpcfxResponse resolveException(final Exception e, final RpcfxResponse resp) {
        e.printStackTrace();
        resp.setStatus(false);
        resp.setException(e);
        return resp;
    }
}
