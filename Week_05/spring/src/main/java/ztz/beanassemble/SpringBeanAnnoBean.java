package ztz.beanassemble;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanAnnoBean {

    @Bean
    public BeanClass getSpringBeanAnnoBean() {

        return new BeanClass();
    }


}
