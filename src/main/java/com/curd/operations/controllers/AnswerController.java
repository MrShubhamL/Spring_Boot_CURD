package com.curd.operations.controllers;

import com.curd.operations.entities.Answer;
import com.curd.operations.services.impl.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    AnswerServiceImpl answerService;
    @PostMapping("/addAnswer")
    public ResponseEntity<Object> addAnswer(@RequestBody Answer answer){
        Answer answer1 = answerService.addAnswer(answer);
        if(answer1!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(answer);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Answer failed to add!");
    }
    @GetMapping("/allAnswers")
    public ResponseEntity<List<Answer>> getAllAnswers(){
        List<Answer> allAnswers = answerService.getAllAnswers();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(allAnswers);
    }

    @DeleteMapping("/deleteAnswer/{id}")
    public boolean deleteAnswer(@PathVariable Long id){
        return answerService.deleteAnswerById(id);
    }
}
