package com.examplerm.rmdemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.pivots.SongPlaylist;
import com.examplerm.rmdemo.entities.projections.SongProjection;

@Repository
public interface ISongPlaylistRepository extends JpaRepository<SongPlaylist, Long>{
    
    @Query(value = "INSERT INTO song_playlist VALUE (:playlistId , :songId)",nativeQuery=true)
    void createRelation(Long playlistId, Long songId);
    
    @Query(value = "select songs.* from song_playlist " +
    "inner join songs on song_playlist.song_id = songs.id " +
    "inner join playlists on song_playlist.playlist_id = playlists.id " +
    "where song_playlist.playlist_id = :playlistId", nativeQuery = true)
    List<SongProjection> listAllSongByIdPlaylist(Long playlistId);

    @Query(value = "DELETE FROM podcast_library WHERE podcast_id= :playlistId", nativeQuery= true)
    void deleteSongsByIdPlaylist(Long playlistId);
}
