package com.example.visitors;


import com.example.users.Student;
import com.example.users.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Principal implements Visitor {
    private Logger logger = LoggerFactory.getLogger(Principal.class);
    public void visit(Student student) {
        logger.info("学⽣信息 姓名：{} 班级：{}", student.name,
                student.clazz);
    }
    public void visit(Teacher teacher) {
        logger.info("学⽣信息 姓名：{} 班级：{} 升学率：{}", teacher.name,
                teacher.clazz, teacher.entranceRatio());
    }
}
