package demo;

public class NormalObj implements NormalInterface{

    @Override
    public String test() {
        System.out.println("--------------------------------这是真实方法，执行真正的业务--------------------------------");
        return "this is real";
    }
}
