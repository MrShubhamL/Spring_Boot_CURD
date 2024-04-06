package com.curd.operations.services;

import com.curd.operations.entities.Parent;

import java.util.List;

public interface ParentService {
    public boolean createParent(Parent parent);
    public List<Parent> getAllActiveParents(boolean b);
    public Parent updateParentInfo(Parent parent);
    public Parent getParentById(Long id);
}
