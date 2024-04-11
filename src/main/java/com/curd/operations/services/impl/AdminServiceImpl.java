package com.curd.operations.services.impl;

import com.curd.operations.entities.Parent;
import com.curd.operations.entities.Student;
import com.curd.operations.repositories.ParentRepository;
import com.curd.operations.repositories.StudentRepository;
import com.curd.operations.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ParentRepository parentRepository;
    @Override
    public Student setStudentEnable(Long id) {
        Student studentById = studentRepository.getStudentById(id);
        Parent parentByStudentId = parentRepository.getParentByStudentId(studentById.getId());
        Student st = new Student();
        st.setId(studentById.getId());
        st.setEmail(studentById.getEmail());
        st.setPassword(studentById.getPassword());
        st.setFirstName(studentById.getFirstName());
        st.setLastName(studentById.getLastName());
        st.setDateOfBirth(studentById.getDateOfBirth());
        st.setContact(studentById.getContact());
        st.setDateOfJoin(studentById.getDateOfJoin());
        st.setLastLoginDate(studentById.getLastLoginDate());
        st.setEnabled(true);
        st.setParents(parentByStudentId);
        return studentRepository.save(st);
    }
}
