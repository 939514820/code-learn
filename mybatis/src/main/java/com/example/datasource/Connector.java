package com.example.datasource;


import java.sql.*;

public class Connector {
    private Connection conn;

    public Connection getConnection() {

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

//    public User execute(String sql) {
//        User user = new User();
//        // 5.执行SQL
//        // 4.执行SQL的对象
//        Statement sta = null;
//        try {
//            sta = conn.prepareStatement(sql);
//            ResultSet rs = sta.executeQuery(sql);
//            while (rs.next()) {
//                // TODO 写死
//                Object id = rs.getObject("id");
//                Object name = rs.getObject("name");
//                Object age = rs.getObject("age");
//                user.setId((int) id);
//                user.setName((String) name);
//                user.setAge((Integer) age);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return user;
//    }
}
