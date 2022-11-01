package com.examplerm.rmdemo.repositories;

import com.examplerm.rmdemo.entities.projections.PlaylistProjection;
import com.examplerm.rmdemo.entities.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.pivots.UserPlaylist;

import java.util.List;

@Repository
public interface IUserPlaylistRepository extends JpaRepository<UserPlaylist, Long> {

    @Query(value = "select playlists.* from user_playlist " +
            "inner join playlists on user_playlist.playlist_id = playlists.id " +
            "inner join users on user_playlist.user_id = users.id " +
            "where user_playlist.user_id = :userId", nativeQuery = true)
    List<PlaylistProjection> listAllPlaylistsByIdUser(Long userId);

    @Query(value = "select users.* from user_playlist " +
            "inner join playlists on user_playlist.playlist_id = playlists.id " +
            "inner join users on user_playlist.user_id = users.id " +
            "where user_playlist.playlist_id = :playlistId", nativeQuery = true)
    List<UserProjection> listAllUsersByIdPlaylist(Long playlistId);

    @Query(value = "DELETE FROM user_playlist WHERE user_id= :userId AND playlist_id= :playlistId", nativeQuery= true)
    void deletePlaylistbyIdUserId(Long playlistId, Long userId);

}
