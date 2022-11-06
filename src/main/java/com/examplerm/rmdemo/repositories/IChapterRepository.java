package com.examplerm.rmdemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.Chapter;

@Repository
public interface IChapterRepository extends JpaRepository<Chapter,Long>{
    @Query(value = "SELECT * FROM chapers WHERE name=:name", nativeQuery = true)
    Optional<Chapter> findByName(String name);
}
