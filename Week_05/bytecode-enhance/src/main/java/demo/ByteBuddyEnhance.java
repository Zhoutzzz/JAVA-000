package demo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ByteBuddyEnhance {

    static ByteBuddy buddy = new ByteBuddy();


    public static Object enhance(Object target) throws Exception{

        NormalInterface enhanceObj = (NormalInterface) buddy.subclass(target.getClass())
                .implement(NormalInterface.class)
                .method(named("test"))
                .intercept(MethodDelegation
                        .withDefaultConfiguration()
                        .filter(named("interceptor"))
                        .to(AOPInterceptor.class))
                .make()
                .load(NormalInterface.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();

        return enhanceObj;
    }
}
