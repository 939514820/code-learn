package com.example.visitors;

import com.example.users.Student;
import com.example.users.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Parent implements Visitor {
    private Logger logger = LoggerFactory.getLogger(Parent.class);
    public void visit(Student student) {
        logger.info("学⽣信息 姓名：{} 班级：{} 排名：{}", student.name,
                student.clazz, student.ranking());
    }
    public void visit(Teacher teacher) {
        logger.info("⽼师信息 姓名：{} 班级：{} 级别：{}", teacher.name,
                teacher.clazz, teacher.identity);
    }
}
