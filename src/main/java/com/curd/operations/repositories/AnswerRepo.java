package com.curd.operations.repositories;

import com.curd.operations.entities.Answer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {
    @Transactional
    Integer deleteAnswerById(Long id);
}
