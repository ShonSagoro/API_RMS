package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplerm.rmdemo.entities.Library;

public interface ILibraryRepository extends JpaRepository<Library,Long>{
    
}
