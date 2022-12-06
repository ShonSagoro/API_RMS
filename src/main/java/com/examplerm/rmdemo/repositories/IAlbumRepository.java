package com.examplerm.rmdemo.repositories;

import com.examplerm.rmdemo.entities.Album;

import java.util.List;
import java.util.Optional;

import com.examplerm.rmdemo.entities.projections.AlbumProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlbumRepository extends JpaRepository<Album,Long> {

    @Query(value = "SELECT * FROM albums WHERE name=:name", nativeQuery = true)
    Optional<Album> findByName(String name);

    @Query(value = "select * from albums WHERE artist_id= :artistId", nativeQuery= true)
    List<AlbumProjection> getAlbumsByArtistId(Long artistId);
}
