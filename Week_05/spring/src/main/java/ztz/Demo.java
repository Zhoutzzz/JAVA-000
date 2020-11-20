package ztz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.boot.starter.ztz.AutoConfigurationDemo;
import ztz.custom.Clazz;
import ztz.custom.School;
import ztz.custom.Student;

//@SpringBootApplication
public class Demo {

//    @Autowired
//    AutoConfigurationDemo autoConfigurationDemo;
//
//    public static void main(String[] args) {
//        ConfigurableApplicationContext run = SpringApplication.run(Demo.class);
//        run.getBean(Demo.class).test();
//    }
//
//    public void test() {
//        System.out.println(autoConfigurationDemo);
//    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        Student zhangsan = context.getBean("s1", Student.class);
        Student lisi = context.getBean("s2", Student.class);
        Student zhaoliu = context.getBean("s3", Student.class);
        Student chenqi = context.getBean("s4", Student.class);
        Clazz clazz = context.getBean("c1", Clazz.class);
        School school = context.getBean("sch1", School.class);
        System.out.println(zhangsan);
        System.out.println(lisi);
        System.out.println(zhaoliu);
        System.out.println(chenqi);
        System.out.println(clazz);
        System.out.println(school);
    }

//    public void test1() {
//        System.out.println(autoConfigurationDemo);
//    }
}
