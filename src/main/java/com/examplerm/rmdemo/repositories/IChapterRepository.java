package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.Chapter;

@Repository
public interface IChapterRepository extends JpaRepository<Chapter,Long>{
    
}
