package com.curd.operations.controllers;

import com.curd.operations.entities.Question;
import com.curd.operations.services.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionServiceImpl questionService;

    @PostMapping("/addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        Question que = questionService.addQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(que);
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        List<Question> allQuestions = questionService.getAllQuestions();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(allQuestions);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public Boolean deleteQuestionById(@PathVariable Long id){
        return questionService.deleteQuestionById(id);
    }
}
