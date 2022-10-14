package com.examplerm.rmdemo.repositories.pivotsRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplerm.rmdemo.entities.pivots.Artist_Library;

public interface IArtist_LibraryRepository extends JpaRepository<Artist_Library, Long>{
    
}
