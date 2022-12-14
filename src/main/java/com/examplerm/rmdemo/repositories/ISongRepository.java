package com.examplerm.rmdemo.repositories;
import java.util.Optional;

import com.examplerm.rmdemo.entities.projections.SongProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.examplerm.rmdemo.entities.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISongRepository extends JpaRepository<Song, Long>{

    @Query(value = "SELECT * FROM songs WHERE name=:name", nativeQuery = true)
    Optional<Song> findByName(String name);

    @Query(value="SELECT * FROM songs WHERE artist_id=:artistId", nativeQuery = true)
    Optional<Song>findByArtistId(Long artistId);

    @Query(value = "SELECT * FROM songs WHERE album_id= :albumId", nativeQuery= true)
    List<SongProjection> getSongsByAlbumId(Long albumId);

    @Query(value = "SELECT * FROM songs WHERE artist_id= :artistId", nativeQuery= true)
    List<SongProjection>getSongsByArtistId(Long artistId);

}
