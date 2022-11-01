package com.examplerm.rmdemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.pivots.SongPlaylist;
import com.examplerm.rmdemo.entities.projections.PlaylistProjection;
import com.examplerm.rmdemo.entities.projections.SongProjection;

@Repository
public interface ISongPlaylistRepository extends JpaRepository<SongPlaylist, Long>{
    
    @Query(value = "select songs.* from song_playlist " +
    "inner join songs on song_playlist.song_id = songs.id " +
    "inner join playlists on song_playlist.playlist_id = playlists.id " +
    "where song_playlist.playlist_id = :playlistId", nativeQuery = true)
    List<SongProjection> listAllSongByIdPlaylist(Long playlistId);
    
    @Query(value = "select songs.* from song_playlist " +
    "inner join songs on song_playlist.song_id = songs.id " +
    "inner join playlists on song_playlist.playlist_id = playlists.id " +
    "where song_playlist.song_id = :songId", nativeQuery = true)
    List<PlaylistProjection>listAllPlaylistByIdSong(Long songId);

    @Query(value = "DELETE FROM song_playlist WHERE playlist_id= :playlistId", nativeQuery= true)
    void deleteSongsByIdPlaylist(Long playlistId);

    @Query(value = "DELETE FROM song_playlist WHERE playlist_id= :playlistId AND song_id= :songId", nativeQuery= true)
    void deleteSongFromUserByTheirIds(Long songId, Long playlistId);
}
