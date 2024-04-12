package com.curd.operations.services;

import com.curd.operations.controllers.AnswerController;
import com.curd.operations.entities.Answer;

import java.util.List;

public interface AnswerService {
    Answer addAnswer(Answer answer);
    List<Answer> getAllAnswers();
    Boolean deleteAnswerById(Long id);
}
