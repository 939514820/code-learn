package com.example;

import com.example.users.Student;
import com.example.users.Teacher;
import com.example.users.User;
import com.example.visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

public class DataView {
    List<User> userList = new ArrayList<User>();

    public DataView() {
        userList.add(new Student("谢⻜机", "᯿点班", "⼀年⼀班"));
        userList.add(new Student("windy", "᯿点班", "⼀年⼀班"));
        userList.add(new Student("⼤⽑", "普通班", "⼆年三班"));
        userList.add(new Student("Shing", "普通班", "三年四班"));
        userList.add(new Teacher("BK", "特级教师", "⼀年⼀班"));
        userList.add(new Teacher("娜娜Goddess", "特级教师", "⼀年⼀班"));
        userList.add(new Teacher("dangdang", "普通教师", "⼆年三班"));
        userList.add(new Teacher("泽东", "实习教师", "三年四班"));
    }

    // 展示
    public void show(Visitor visitor) {
        for (User user : userList) {
            user.accept(visitor);
        }
    }
    // vistor 定义想看的数据
    // user 定义接受方法 vistor.visit(user);

}
