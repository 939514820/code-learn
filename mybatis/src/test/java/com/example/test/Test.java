package com.example.test;

import com.example.datasource.UnpooledDataSource;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {


        UnpooledDataSource dataSource = new UnpooledDataSource("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false",
                "root", "123456");
        try {
            dataSource.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
