package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplerm.rmdemo.entities.User;

public interface IUserRepository extends JpaRepository<User, Long>{
    
}
