package com.bloggingapp.demo.repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapp.demo.entites.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
