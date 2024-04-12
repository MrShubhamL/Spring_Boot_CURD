package com.curd.operations.services;

import com.curd.operations.entities.Admin;
import com.curd.operations.entities.Student;

public interface AdminService {
    Student setStudentEnable(Long id);
    Admin createAdmin(Admin admin);
    Admin getAdminByEmail(String email);
}
