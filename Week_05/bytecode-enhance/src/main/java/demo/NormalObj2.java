package demo;

public class NormalObj2 implements NormalInterface{

    @Override
    public String test() {
        System.out.println("--------------------------------同一接口下新的真实方法，执行真正的业务--------------------------------");
        return "this is real No.2";
    }
}
