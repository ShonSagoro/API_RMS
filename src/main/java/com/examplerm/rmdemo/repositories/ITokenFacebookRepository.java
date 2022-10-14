package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplerm.rmdemo.entities.TokenFacebook;

public interface ITokenFacebookRepository extends JpaRepository<TokenFacebook,Long> {
    
}
