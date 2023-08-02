package com.example.builder;

import com.example.bind.SqlCommandType;
import com.example.datasource.DataSourceFactory;
import com.example.mapping.MappedStatement;
import com.example.session.Configuration;
import com.example.util.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

public class XMLConfigBuilder {
    private Configuration configuration;
    private Element root;

    public XMLConfigBuilder(Reader file) {
        // 1. 调用父类初始化Configuration
        configuration = new Configuration();
        // 2. dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(file);
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Configuration parse() {
        try {

            // 解析每一个mapper
            List<Element> mapperList = root.element("mappers").elements();
            for (Element element : mapperList) {
                String text = element.getText();
                System.out.println("text=" + text);
                SAXReader saxReader = new SAXReader();
                try {
                    // 解析每一个xml文件
                    File file = new File(text);
                    Document document = saxReader.read(file);
                    root = document.getRootElement();
                    // 解析映射器
                    mapperElement(root);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void mapperElement(Element head) throws Exception {
        List<Element> mapperList = head.elements();
        String namespace = head.attributeValue("namespace");
        for (Element book : mapperList) {
            // mapper节点子元素
//            List<Element> books = sqlitem.elements();
//            for (Element book : books) {
                String id = book.attributeValue("id");
                String parameterType = book.attributeValue("parameterType");
                String resultType = book.attributeValue("resultType");
                System.out.println(id + "_" + parameterType + "_" + resultType);
                String name = book.getName();//获取当前元素名
                String text = book.getText();//获取当前元素值
                System.out.println(":name= " + name + " text: " + text);
                // 添加解析 SQL
                MappedStatement mappedStatements = new MappedStatement();
                mappedStatements.setId(namespace + "." + id);
                Class<?> pClass = Class.forName(parameterType);
                mappedStatements.setParameterType(pClass);
                Class<?> aClass = Class.forName(resultType);
                mappedStatements.setResultType(aClass.newInstance());
                mappedStatements.setSql(text);
                mappedStatements.setSqlCommandType(SqlCommandType.SELECT);
                configuration.addMappedStatement(mappedStatements);
//            }
        }
        if (StringUtil.isNotEmpty(namespace)) {
            // 注册Mapper映射器
            configuration.addMapper(Class.forName(namespace));
        }

    }

    private DataSourceFactory dataSourceElement(XNode context) throws Exception {
        if (context != null) {
            String type = context.getStringAttribute("type");
            Properties props = context.getChildrenAsProperties();
            DataSourceFactory factory = (DataSourceFactory) resolveClass(type).getDeclaredConstructor().newInstance();
            factory.setProperties(props);
            return factory;
        }
        throw new BuilderException("Environment declaration requires a DataSourceFactory.");
    }
}
