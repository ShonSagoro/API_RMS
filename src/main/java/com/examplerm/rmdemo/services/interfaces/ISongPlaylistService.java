package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

@Service
public interface ISongPlaylistService {
    //http://localhost:8080/user/id_user/id_library/id_playlist/id_song

    BaseResponse addSongToPlaylist(Long song_id, Long playlist_id);

    BaseResponse listAllSongByIdPlaylist();

    BaseResponse getPlaylistByIdSong();

    void deleteSongsByIdPlaylist();

    void deleteByIdSongToPlaylist();

}
