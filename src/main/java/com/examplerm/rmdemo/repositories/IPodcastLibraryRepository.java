package com.examplerm.rmdemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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

    @Query(value = "DELETE FROM podcast_library WHERE library_id= :libraryId", nativeQuery= true)
    void deletePodcastsByIdLibrary(Long libraryId);
}