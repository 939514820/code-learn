package com.example.visitors;

import com.example.users.Student;
import com.example.users.Teacher;

public interface Visitor {
    // 访问学⽣信息
    void visit(Student student);
    // 访问⽼师信息
    void visit(Teacher teacher);
}
