package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreateSongPlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

@Service
public interface ISongPlaylistService {

    BaseResponse create(CreateSongPlaylistRequest request);

    BaseResponse listAllSongByIdPlaylist(Long playlistId);

    void deleteSongsByIdPlaylist(Long playlistId);

    void deleteSongByIdSongAndPlaylist(Long songId,Long playlistId);


}
