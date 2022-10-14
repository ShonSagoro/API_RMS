package com.examplerm.rmdemo.repositories.pivotsRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplerm.rmdemo.entities.pivots.Podcast_Library;

public interface IPodcast_LibraryRepository extends JpaRepository<Podcast_Library, Long> {
    
}
