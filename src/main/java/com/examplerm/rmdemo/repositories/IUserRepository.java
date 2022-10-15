package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
    
}
