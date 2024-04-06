package com.curd.operations.controllers;


import com.curd.operations.entities.Parent;
import com.curd.operations.entities.Student;
import com.curd.operations.services.impl.ParentServiceImpl;
import com.curd.operations.services.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/student/request")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private ParentServiceImpl parentService;

    @GetMapping("/test")
    private String testAPI() {
        return "Test API request";
    }

    @PostMapping("/register")
    private ResponseEntity<String> registerStudent(@RequestBody Student std) {
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

    @DeleteMapping("/delete_student/{id}")
    private ResponseEntity<Object> deleteStudentById(@PathVariable Long id) {
        Integer b = studentService.deleteStudent(id);
        if (b == 1) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Details Not Found!");
    }

    @PutMapping("/update_student")
    private ResponseEntity<Object> updateStudentInfo(@RequestBody Student student) {
        Student std = studentService.updateStudentInfo(student);
        if (std != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(std);
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Student Not Updated!");
    }

    @PutMapping("/update_parent/{id}")
    private ResponseEntity<Object> updateParentInfo(@RequestBody Parent parent, @PathVariable Long id) {
        Student studentInfoById = studentService.getStudentInfoById(id);
        parent.setStudent(studentInfoById);
        parent.setLastLoginDate(LocalDate.now().toString());
        Parent p = parentService.updateParentInfo(parent);
        if (p!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(p);
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Parent Not Updated!");
    }

    @GetMapping("/allActiveStudents")
    private ResponseEntity<List<Student>> getAllActiveStudent() {
        List<Student> allActiveStudent = studentService.getAllActiveStudent(true);
        if (allActiveStudent != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(allActiveStudent);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/parent_register")
    private ResponseEntity<String> addParent(@RequestBody Parent parent){
        boolean b = parentService.createParent(parent);
        if(b){
            return ResponseEntity.status(HttpStatus.CREATED).body("Parent is created.");
        }
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Email OR Contact Already Existed!");
    }

    @GetMapping("/allActiveParents")
    private ResponseEntity<List<Parent>> getAllParents() {
        List<Parent> allParents = parentService.getAllActiveParents(true);
        if (allParents != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(allParents);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}
