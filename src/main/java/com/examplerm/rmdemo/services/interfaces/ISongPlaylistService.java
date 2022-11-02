package com.examplerm.rmdemo.services.interfaces;

import com.examplerm.rmdemo.controllers.dtos.request.CreateSongPlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

public interface ISongPlaylistService {

    BaseResponse create(CreateSongPlaylistRequest request);

    BaseResponse listAllSongsByIdPlaylist(Long playlistId);

    BaseResponse listAllPlaylistByIdSong(Long songId);

    void deleteSongsByIdPlaylist(Long playlistId);
    
    void deleteSongFromUserByTheirIds(Long songId,Long playlistId);

}
