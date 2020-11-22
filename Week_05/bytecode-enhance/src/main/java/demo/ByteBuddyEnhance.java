package demo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyEnhance {


    public static void main(String[] args) throws Exception {
        ByteBuddy buddy = new ByteBuddy();
        String o = buddy.subclass(NormalObj.class)
                .method(ElementMatchers.named("gali"))
                .intercept(MethodDelegation
                        .withDefaultConfiguration()
                        .filter(ElementMatchers.named("gali2"))
                        .to(TestObj.class))
                .make()
                .load(NormalObj.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance()
                .gali();

        System.out.println(o);
    }
}
