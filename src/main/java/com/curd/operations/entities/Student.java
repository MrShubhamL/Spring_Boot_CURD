package com.curd.operations.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String contact;
    private String dateOfJoin;
    private String lastLoginDate;
    private boolean enabled;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "student", fetch = FetchType.LAZY)
    private Parent parents;
}
