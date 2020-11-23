package demo;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("this is an perform monitor agent.");

        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
            return builder
                    .declaredTypes(NormalInterface.class)
                    .method(ElementMatchers.named("test")) // 拦截任意方法
                    .intercept(MethodDelegation.to(AOPInterceptor.class)); // 委托
        };

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {


            @Override
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
//                System.out.println("agent发现");
            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {
//                System.out.println("agent传输");
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {
//                System.out.println("agent忽略");
            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {
                System.out.println("agent错误:" + s);
                System.out.println("------------------"+ javaModule + "-----------------------");
                throwable.printStackTrace();
            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
//                System.out.println("agent完成");
            }
        };

        new AgentBuilder
                .Default()
                .type(ElementMatchers.nameStartsWith("demo")) // 指定需要拦截的类
                .transform(transformer)
                .with(listener)
                .installOn(inst);
    }
}
