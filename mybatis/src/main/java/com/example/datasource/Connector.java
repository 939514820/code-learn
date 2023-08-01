package com.example.datasource;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.sql.*;
import java.util.List;

public class Connector {
    private Connection conn;

    public Connection getConnection() {

        File file = new File("D:\\IdeaProjects\\code-learn\\mybatis\\src\\test\\java\\resources\\datasource.xml");
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            List<Element> config = rootElement.elements();
            for (Element element : config) {
                // 设置值到source中
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

// 2.用户信息和url
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false";
        String user = "root";
        String password = "123456";
        // 3.获取连接
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
