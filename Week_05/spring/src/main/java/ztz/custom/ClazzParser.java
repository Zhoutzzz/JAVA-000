package ztz.custom;

import org.springframework.beans.factory.config.ListFactoryBean;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ClazzParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Clazz.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        BeanDefinitionRegistry registry = parserContext.getRegistry();
        NodeList nodeList = element.getChildNodes();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ListFactoryBean.class);
        ManagedList<RuntimeBeanReference> managedList = new ManagedList<>();
        for (int i = 0; i<nodeList.getLength();i++){
            Node node = nodeList.item(i);
            NamedNodeMap nodeMap = node.getAttributes();
            if (!ObjectUtils.isEmpty(nodeMap)){
                Node refNode = nodeMap.getNamedItem("ref");
                Node nameNode = nodeMap.getNamedItem("name");
                String[] refNameArray = refNode.getNodeValue().split(",");
                for (String refName : refNameArray){
                    RuntimeBeanReference reference = new RuntimeBeanReference(refName);
                    managedList.add(reference);
                }
                beanDefinitionBuilder.addPropertyValue("sourceList", managedList);
                registry.registerBeanDefinition("stu#" + i, beanDefinitionBuilder.getBeanDefinition());
                builder.addPropertyValue("className", element.getAttribute("className"));
                builder.addPropertyReference(nameNode.getNodeValue(), "stu#" + i);

            }
        }
    }
}
