package com.examplerm.rmdemo.repositories;

import com.examplerm.rmdemo.entities.projections.AlbumProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.pivots.AlbumLibrary;

import java.util.List;

@Repository
public interface IAlbumLibraryRepository extends JpaRepository<AlbumLibrary, Long> {

    @Query(value = "select albums.* from album_library " +
            "inner join albums on album_library.album_id = albums.id " +
            "inner join libraries on album_library.library_id = libraries.id " +
            "where album_library.library_id = :libraryId", nativeQuery = true)
    List<AlbumProjection> listAllAlbumsByLibraryId(Long libraryId);


    @Query(value = "DELETE FROM album_library WHERE library_id= :libraryId AND album_id= :albumId", nativeQuery= true)
    void deleteAlbumsByIdFromLibraryId(Long albumId, Long libraryId);

}
