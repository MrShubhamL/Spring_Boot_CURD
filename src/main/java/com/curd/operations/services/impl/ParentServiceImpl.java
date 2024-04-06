package com.curd.operations.services.impl;

import com.curd.operations.entities.Parent;
import com.curd.operations.entities.Student;
import com.curd.operations.repositories.ParentRepository;
import com.curd.operations.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ParentServiceImpl implements ParentService {
    @Autowired
    ParentRepository parentRepository;
    @Override
    public boolean createParent(Parent parent) {
        parent.setLastLoginDate(LocalDate.now().toString());
        parent.setEnabled(false);
        boolean b1 = parentRepository.existsByEmail(parent.getEmail());
        boolean b2 = parentRepository.existsByContact(parent.getContact());
        if(b1){
            return false;
        }
        else if(b2){
            return false;
        }
        parentRepository.save(parent);
        return true;
    }

    @Override
    public List<Parent> getAllActiveParents(boolean b) {
        return parentRepository.getAllByEnabled(true);
    }

    @Override
    public Parent updateParentInfo(Parent p) {
        return parentRepository.save(p);
    }

    @Override
    public Parent getParentById(Long id) {
        return parentRepository.getParentById(id);
    }


}
