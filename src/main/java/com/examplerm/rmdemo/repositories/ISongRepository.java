package com.examplerm.rmdemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examplerm.rmdemo.entities.Song;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepository extends JpaRepository<Song, Long>{
    @Query(value = "SELECT * FROM songs WHERE name=:name", nativeQuery = true)
    Optional<Song> findByName(String name);

    @Query(value = "SELECT * FROM songs WHERE album_id=:albumId", nativeQuery = true)
    Optional<Song>findByAlbumId(Long albumId);

    @Query(value="SELECT * FROM songs WHERE artist_id=:artistId", nativeQuery = true)
    Optional<Song>findByArtistId(Long artistId);
}
