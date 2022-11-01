package com.examplerm.rmdemo.services.interfaces;

import com.examplerm.rmdemo.controllers.dtos.request.CreateSongPlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

public interface ISongPlaylistService {

    BaseResponse create(CreateSongPlaylistRequest request);

    BaseResponse listAllSongByIdPlaylist(Long playlistId);

    void deleteSongsByIdPlaylist(Long playlistId);


}
