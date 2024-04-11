package com.curd.operations.repositories;

import com.curd.operations.entities.Parent;
import com.curd.operations.entities.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    boolean existsByEmail(String email);
    boolean existsByContact(String email);
    Parent getParentById(Long id);
    Parent getParentByStudentId(Long id);

}
