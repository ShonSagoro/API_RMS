package com.examplerm.rmdemo.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdatePlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Playlist;

public interface IPlaylistService {

    BaseResponse create(CreatePlaylistRequest request);

    BaseResponse uploadPhoto(MultipartFile file);
    
    BaseResponse update(Long id, UpdatePlaylistRequest request);
    
    BaseResponse get(Long id);
    
    BaseResponse list();

    void delete(Long id);

    Playlist findById(Long id);
    
}