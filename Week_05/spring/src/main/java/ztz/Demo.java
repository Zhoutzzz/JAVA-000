package ztz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import spring.boot.starter.ztz.AutoConfigurationDemo;

@SpringBootApplication
public class Demo {

    @Autowired
    AutoConfigurationDemo autoConfigurationDemo;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Demo.class);
        run.getBean(Demo.class).test();
    }

    public void test() {
        System.out.println(autoConfigurationDemo);
    }

//    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
//        ApplicationContext context1 = new AnnotationConfigApplicationContext("ztz.beanassemble");
//
//
//        System.out.println(context.getBean(SpringBeanXml.class));
//        System.out.println(context1.getBean(SpringBeanAnnoComponent.class));
//        BeanClass bean = context.getBean(BeanClass.class);
//        SpringBeanAnnoBean bea1n = context.getBean(SpringBeanAnnoBean.class);
////        AutoConfigurationDemo bean1 = context1.getBean(AutoConfigurationDemo.class);
////        System.out.println();
//        System.out.println(bea1n);
//        System.out.println(bean);
//        new Demo().test1();
//    }

    public void test1() {
        System.out.println(autoConfigurationDemo);
    }
}
