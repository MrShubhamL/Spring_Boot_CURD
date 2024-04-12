package com.curd.operations.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    @JsonBackReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answer;
}
