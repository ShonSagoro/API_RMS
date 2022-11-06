package com.examplerm.rmdemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.Podcast;

@Repository
public interface IPodcastRepository extends JpaRepository<Podcast, Long> {
    @Query(value = "SELECT * FROM podcasts WHERE name=:name", nativeQuery = true)
    Optional<Podcast> findByName(String name);
}
