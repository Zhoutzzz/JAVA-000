package demo;


import net.bytebuddy.implementation.bind.annotation.Super;

public class TestObj {
    public static String gali1() {
        System.out.println("真实方法1");
        return "success";
    }

    public static String gali2(@Super NormalObj normalObj) {
        System.out.println("真实方法2");
        normalObj.gali();
        return "success";
    }
}