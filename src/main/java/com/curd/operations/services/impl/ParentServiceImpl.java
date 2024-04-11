package com.curd.operations.services.impl;

import com.curd.operations.entities.Parent;
import com.curd.operations.entities.Student;
import com.curd.operations.repositories.ParentRepository;
import com.curd.operations.repositories.StudentRepository;
import com.curd.operations.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ParentServiceImpl implements ParentService {
    @Autowired
    ParentRepository parentRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public Parent createParent(Parent parent) {
        parent.setLastLoginDate(LocalDate.now().toString());
        Student studentById = studentRepository.getStudentById(parent.getStudent().getId());
        if(studentById!=null){
            Parent parentByStudentId = parentRepository.getParentByStudentId(studentById.getId());
            if(parentByStudentId==null){
                return parentRepository.save(parent);
            }
        }
        return null;
    }

    @Override
    public Parent updateParentInfo(Parent p) {
        return parentRepository.save(p);
    }

    @Override
    public Parent getParentById(Long id) {
        return parentRepository.getParentById(id);
    }

    @Override
    public Parent getParentByStudentId(Long id) {
        return parentRepository.getParentByStudentId(id);
    }


}
