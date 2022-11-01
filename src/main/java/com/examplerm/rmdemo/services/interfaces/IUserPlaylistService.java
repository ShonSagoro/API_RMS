package com.examplerm.rmdemo.services.interfaces;

import com.examplerm.rmdemo.controllers.dtos.request.CreateUserPlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

public interface IUserPlaylistService {

    BaseResponse create(CreateUserPlaylistRequest request);

    BaseResponse listAllPlaylistsByUserId(Long userId);

    BaseResponse listAllUsersByPlaylistId(Long playlistId);

    void deletePlaylistByIdfromUserId(Long playlistId, Long userId);

}
