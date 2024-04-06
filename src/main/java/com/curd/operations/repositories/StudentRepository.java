package com.curd.operations.repositories;

import com.curd.operations.entities.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentById(Long id);
    boolean existsByEmail(String email);
    boolean existsByContact(String email);
    @Transactional
    Integer deleteStudentById(Long id);
    List<Student> getAllByEnabled(Boolean b);
    Student getStudentByEmailAndPassword(String email, String password);
}
