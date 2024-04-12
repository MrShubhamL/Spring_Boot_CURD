package com.curd.operations.services;

import com.curd.operations.entities.Question;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question question);
    List<Question> getAllQuestions();
    Boolean deleteQuestionById(Long id);
}
