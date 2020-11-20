package ztz.custom;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNameSpaceHandle extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("student", new StudentParser());
        registerBeanDefinitionParser("clazz", new ClazzParser());
        registerBeanDefinitionParser("school", new SchoolParser());
    }
}
