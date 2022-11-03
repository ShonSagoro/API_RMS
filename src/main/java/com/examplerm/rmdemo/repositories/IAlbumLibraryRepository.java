package com.examplerm.rmdemo.repositories;

import com.examplerm.rmdemo.entities.projections.AlbumProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examplerm.rmdemo.entities.pivots.AlbumLibrary;

import java.util.List;

@Repository
public interface IAlbumLibraryRepository extends JpaRepository<AlbumLibrary, Long> {

    @Query(value = "select albums.* from album_library " +
            "inner join albums on album_library.album_id = albums.id " +
            "inner join libraries on album_library.library_id = libraries.id " +
            "where album_library.library_id = :libraryId", nativeQuery = true)
    List<AlbumProjection> listAllAlbumsByLibraryId(Long libraryId);

    @Transactional
    @Modifying
    @Query(value = "delete from album_library where library_id= :libraryId and album_id= :albumId", nativeQuery = true)
    void deleteAlbumFromLibraryByThierIds(Long albumId, Long libraryId);

}
