package com.curd.operations.services.impl;

import com.curd.operations.entities.Parent;
import com.curd.operations.entities.Student;
import com.curd.operations.repositories.ParentRepository;
import com.curd.operations.repositories.StudentRepository;
import com.curd.operations.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ParentRepository parentRepository;
    @Override
    public boolean registerStudent(Student stud) {
        LocalDate date = LocalDate.now();
        stud.setLastLoginDate(date.toString());
        stud.setEnabled(false);
        boolean b1 = studentRepository.existsByEmail(stud.getEmail());
        boolean b2 = studentRepository.existsByContact(stud.getContact());
        if(b1){
            return false;
        } else if (b2) {
            return false;
        }
        studentRepository.save(stud);
        return true;
    }

    @Override
    public Student loginStudent(String email, String password) {
        return studentRepository.getStudentByEmailAndPassword(email, password);
    }

    @Override
    public Student updateStudentInfo(Student student) {
        LocalDate date = LocalDate.now();
        student.setLastLoginDate(date.toString());
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentInfoById(Long id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public Integer deleteStudent(Long id) {
        return studentRepository.deleteStudentById(id);
    }

    @Override
    public List<Student> getAllActiveStudent(Boolean b) {
        return studentRepository.getAllByEnabled(b);
    }
}
