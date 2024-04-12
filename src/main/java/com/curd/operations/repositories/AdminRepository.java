package com.curd.operations.repositories;

import com.curd.operations.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin getAdminByEmail(String email);
}
