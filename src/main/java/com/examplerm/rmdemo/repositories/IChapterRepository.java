package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examplerm.rmdemo.entities.Chapter;

public interface IChapterRepository extends JpaRepository<Chapter,Long>{
    
}
