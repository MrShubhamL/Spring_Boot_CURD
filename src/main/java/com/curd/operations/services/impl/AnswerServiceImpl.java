package com.curd.operations.services.impl;

import com.curd.operations.entities.Answer;
import com.curd.operations.entities.Question;
import com.curd.operations.repositories.AnswerRepo;
import com.curd.operations.repositories.QuestionRepo;
import com.curd.operations.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepo answerRepo;
    @Autowired
    QuestionRepo questionRepo;
    @Override
    public Answer addAnswer(Answer answer) {
        Question questionsById = questionRepo.getQuestionsById(answer.getQuestion().getId());
        answer.setQuestion(questionsById);
        return answerRepo.save(answer);
    }
    @Override
    public List<Answer> getAllAnswers() {
        return answerRepo.findAll();
    }
    @Override
    public Boolean deleteAnswerById(Long id) {
        Integer i = answerRepo.deleteAnswerById(id);
        return i != 0;
    }
}
