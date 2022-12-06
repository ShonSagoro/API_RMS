package com.examplerm.rmdemo.repositories;

import java.util.List;
import java.util.Optional;

import com.examplerm.rmdemo.entities.projections.ChapterProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.Chapter;

@Repository
public interface IChapterRepository extends JpaRepository<Chapter,Long>{
    @Query(value = "SELECT * FROM chapters WHERE name=:name", nativeQuery = true)
    Optional<Chapter> findByName(String name);

    @Query(value = "select * from chapters WHERE podcast_id= :podcastId", nativeQuery= true)
    List<ChapterProjection> getChaptersByPodcastId(Long podcastId);
}
