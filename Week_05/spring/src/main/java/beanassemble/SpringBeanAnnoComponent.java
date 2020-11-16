package beanassemble;

import org.springframework.stereotype.Component;

@Component
public class SpringBeanAnnoComponent {

    public void anno() {
        System.out.println("Component注解装配");
    }
}
