package com.curd.operations.repositories;

import com.curd.operations.entities.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
    Question getQuestionsById(Long id);
    @Transactional
    Integer deleteQuestionById(Long id);
}
