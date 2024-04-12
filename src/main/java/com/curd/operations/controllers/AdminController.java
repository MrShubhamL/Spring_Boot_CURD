package com.curd.operations.controllers;

import com.curd.operations.entities.Admin;
import com.curd.operations.entities.Parent;
import com.curd.operations.entities.Student;
import com.curd.operations.services.impl.AdminServiceImpl;
import com.curd.operations.services.impl.ParentServiceImpl;
import com.curd.operations.services.impl.StudentServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    ParentServiceImpl parentService;
    @Autowired
    AdminServiceImpl adminService;

    @PostConstruct
    public void createAdmin(){
        Admin admin = new Admin();
        admin.setName("Admin");
        admin.setContact("7769048180");
        admin.setEmail("admin123@gmail.com");
        admin.setPassword("admin123");
        Admin adminByEmail = adminService.getAdminByEmail(admin.getEmail());
        if(adminByEmail==null){
            adminService.createAdmin(admin);
            System.out.println("Admin Created.");
        }
    }


    @DeleteMapping("/delete_student/{id}")
    private ResponseEntity<Object> deleteStudentById(@PathVariable Long id) {
        Integer b = studentService.deleteStudent(id);
        if (b == 1) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Details Not Found!");
    }


    @GetMapping("/allActiveStudents")
    private ResponseEntity<List<Student>> getAllActiveStudent() {
        List<Student> allActiveStudent = studentService.getAllActiveStudent(true);
        if (allActiveStudent != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(allActiveStudent);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    @PostMapping("/setStudentEnabledById/{id}")
    private ResponseEntity<Object> setStudentEnableById(@PathVariable Long id){
        Student student = adminService.setStudentEnable(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Objects.requireNonNullElse(student, "Student info not updated!"));
    }


}
