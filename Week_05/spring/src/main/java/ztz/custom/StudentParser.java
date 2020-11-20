package ztz.custom;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StudentParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Student.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i<nodeList.getLength();i++){
            Node node = nodeList.item(i);
            NamedNodeMap nodeMap = node.getAttributes();
            if (!ObjectUtils.isEmpty(nodeMap)){
                for (int i1 = 0; i1 < nodeMap.getLength(); i1++) {
                    String name = nodeMap.item(i1).getNodeName();
                    String value = nodeMap.item(i1).getNodeValue();
                    builder.addPropertyValue(name, value);
                }
            }
        }
    }
}
