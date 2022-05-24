package com.employee_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_app.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
 