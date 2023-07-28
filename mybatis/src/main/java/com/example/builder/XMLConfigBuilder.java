package com.example.builder;

import com.example.bind.SqlCommandType;
import com.example.mapping.MappedStatement;
import com.example.session.Configuration;
import com.example.transaction.TransactionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.io.File;
import java.util.List;
import java.util.Properties;

public class XMLConfigBuilder {
    private Configuration configuration;
    private Element root;

    public XMLConfigBuilder(File file) {
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
            // 解析映射器
            mapperElement();
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void mapperElement() throws Exception {
        List<Element> mapperList = root.elements();
        String namespace = mapperList.get(0).attributeValue("namespace");
        for (Element sqlitem : mapperList) {
            // mapper节点子元素
            List<Element> books = sqlitem.elements();
            for (Element book : books) {
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
                mappedStatements.setParameterType(parameterType);
                mappedStatements.setResultType(resultType);
                mappedStatements.setSql(text);
                mappedStatements.setSqlCommandType(SqlCommandType.SELECT);
                configuration.addMappedStatement(mappedStatements);
            }
        }
        // 注册Mapper映射器
        configuration.addMapper(Class.forName(namespace));
    }


}
