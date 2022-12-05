package com.examplerm.rmdemo.repositories;

import com.examplerm.rmdemo.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE name=:name", nativeQuery = true)
    Optional<User> findByName(String name);

    @Query(value="SELECT * FROM users WHERE name=:name AND password=:password", nativeQuery = true)
    Optional<User> findByNameAndPassword(String name, String password);


    Optional<User> findOneByEmail(String email);
}
