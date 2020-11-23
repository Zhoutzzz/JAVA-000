package demo;

public class Test {
    public static void main(String[] args) throws Exception {
        NormalInterface normalObj = new NormalObj2();
        normalObj.test();
        System.out.println();
        System.out.println("++++++++++++++++++++++分割线区分++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++分割线区分++++++++++++++++++++++++");
        System.out.println();
        normalObj = new NormalObj();
        normalObj.test();
    }
}
