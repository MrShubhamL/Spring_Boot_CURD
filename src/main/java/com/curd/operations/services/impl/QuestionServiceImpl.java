package com.curd.operations.services.impl;

import com.curd.operations.entities.Question;
import com.curd.operations.repositories.QuestionRepo;
import com.curd.operations.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepo questionRepo;

    @Override
    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    @Override
    public Boolean deleteQuestionById(Long id) {
        Integer i = questionRepo.deleteQuestionById(id);
        return i != 0;
    }
}
