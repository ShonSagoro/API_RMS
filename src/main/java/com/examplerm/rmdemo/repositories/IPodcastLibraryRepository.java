package com.examplerm.rmdemo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.pivots.PodcastLibrary;
import com.examplerm.rmdemo.entities.projections.PodcastProjection;

@Repository
public interface IPodcastLibraryRepository extends JpaRepository<PodcastLibrary, Long> {
    
    @Query(value = "select podcasts.* from podcast_library " +
    "inner join libraries on podcast_library.library_id = libraries.id " +
    "inner join podcasts on podcast_library.podcast_id = podcasts.id " +
    "where podcast_library.library_id = :libraryId", nativeQuery = true)
    List<PodcastProjection> listAllPodcastByLibraryId(Long libraryId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM podcast_library WHERE podcast_id= :podcastId and library_id= :libraryId", nativeQuery= true)
    void deletePodcastFromLibraryByThierIds(Long podcastId, Long libraryId);

}