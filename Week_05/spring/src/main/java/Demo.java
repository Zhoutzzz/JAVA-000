import beanassemble.BeanClass;
import beanassemble.SpringBeanAnnoBean;
import beanassemble.SpringBeanAnnoComponent;
import beanassemble.SpringBeanXml;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        ApplicationContext context1 = new AnnotationConfigApplicationContext("beanassemble");


        System.out.println(context.getBean(SpringBeanXml.class));
        System.out.println(context1.getBean(SpringBeanAnnoComponent.class));
        BeanClass bean = context.getBean(BeanClass.class);
        SpringBeanAnnoBean bea1n = context.getBean(SpringBeanAnnoBean.class);
        System.out.println(bea1n);
        System.out.println(bean);
//        Object o = new
    }
}
