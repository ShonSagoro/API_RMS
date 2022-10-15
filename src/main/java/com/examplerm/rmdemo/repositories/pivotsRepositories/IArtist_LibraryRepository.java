package com.examplerm.rmdemo.repositories.pivotsRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.pivots.Artist_Library;

@Repository
public interface IArtist_LibraryRepository extends JpaRepository<Artist_Library, Long>{
    
}
