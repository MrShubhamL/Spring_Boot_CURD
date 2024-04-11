package com.curd.operations.services;

import com.curd.operations.entities.Parent;

import java.util.List;

public interface ParentService {
    public Parent createParent(Parent parent);
    public Parent updateParentInfo(Parent parent);
    public Parent getParentById(Long id);
    public Parent getParentByStudentId(Long id);
}
