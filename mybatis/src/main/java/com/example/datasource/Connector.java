package com.example.datasource;


import com.example.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.sql.*;
import java.util.List;

@Slf4j
public class Connector {
    private Connection conn;

    public Connection getConnection() {
        String url = "";
        String user = "";
        String password = "";
        String driver = "";
        try {
            Reader resource = Resources.getResourceAsReader("mybatis-config.xml");
            if (null == resource) {
                throw new RuntimeException();
            }
            SAXReader reader = new SAXReader();
            Document document = reader.read(resource);
            // datasources
            Element datasources = document.getRootElement().element("datasources");
            // datasorce
            List<Element> datasource = datasources.elements();
            for (Element element : datasource) {
                // 设置值到source中
                url = element.element("url").getText();
                user = element.element("username").getText();
                password = element.element("password").getText();
                driver = element.element("driver").getText();
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 2.用户信息和url

        // 3.获取连接
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public void close() {
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
