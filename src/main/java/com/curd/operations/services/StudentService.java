package com.curd.operations.services;

import com.curd.operations.entities.Student;

import java.util.List;

public interface StudentService {
    boolean registerStudent(Student stud);
    Student loginStudent(String email, String password);
    Student updateStudentInfo(Student student);
    Student getStudentInfoById(Long id);
    Integer deleteStudent(Long id);
    List<Student> getAllActiveStudent(Boolean b);
}
