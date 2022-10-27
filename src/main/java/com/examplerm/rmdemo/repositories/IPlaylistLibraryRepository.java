package com.examplerm.rmdemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.pivots.PlaylistLibrary;
import com.examplerm.rmdemo.entities.projections.PlaylistProjection;

@Repository
public interface IPlaylistLibraryRepository extends JpaRepository<PlaylistLibrary, Long>{
    
    @Query(value = "select playlists.* from playlist_library " +
    "inner join libraries on playlist_library.library_id = libraries.id " +
    "inner join playlists on playlist_library.podcast_id = playlists.id " +
    "where playlist_library.library_id = :libraryId", nativeQuery = true)
    List<PlaylistProjection> listAllPlaylistByLibraryId(Long libraryId);

    @Query(value = "DELETE FROM playlist_library WHERE library_id= :libraryId", nativeQuery= true)
    void deletePlaylistsByIdLibrary(Long libraryId);
}
