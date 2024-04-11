package com.curd.operations.controllers;


import com.curd.operations.entities.Parent;
import com.curd.operations.entities.Student;
import com.curd.operations.services.impl.ParentServiceImpl;
import com.curd.operations.services.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student/request")
@CrossOrigin("*")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private ParentServiceImpl parentService;

    @PostMapping("/register")
    private ResponseEntity<Object> registerStudent(@RequestBody Student std) {
        boolean student = studentService.registerStudent(std);
        if (student) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Student Created Successfully");
        }
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Email OR Contact Already Existed!");
    }

    @GetMapping("/login")
    private ResponseEntity<Student> loginStudent(@RequestBody Student student) {
        String email = student.getEmail();
        String password = student.getPassword();
        Student studentDetails = studentService.loginStudent(email, password);
        if (studentDetails != null) {
            return ResponseEntity.ok(studentDetails);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @GetMapping("/student_info/{id}")
    private ResponseEntity<Object> getStudentById(@PathVariable Long id) {
        try {
            Student studentInfoById = studentService.getStudentInfoById(id);
            if (studentInfoById != null) {
                return ResponseEntity.ok(studentInfoById);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/update_student")
    private ResponseEntity<Object> updateStudentInfo(@RequestBody Student student) {
        Student std = studentService.updateStudentInfo(student);
        if (std != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(std);
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Student Not Updated!");
    }

    @PutMapping("/update_parent")
    private ResponseEntity<Object> updateParentInfo(@RequestBody Parent parent) {
        Student studentInfoById = studentService.getStudentInfoById(parent.getStudent().getId());
        parent.setLastLoginDate(LocalDate.now().toString());
        parent.setStudent(studentInfoById);
        if(studentInfoById!=null){
            Parent p = parentService.updateParentInfo(parent);
            if (p!=null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(p);
            }
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Parent Not Updated!");
    }


    @PostMapping("/parent_register")
    private ResponseEntity<Object> addParent(@RequestBody Parent parent){
            Parent parent1 = parentService.createParent(parent);
            if(parent1!=null){
                return ResponseEntity.status(HttpStatus.CREATED).body(parent1);
            }
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Parent Details Already Added!");
    }



}
