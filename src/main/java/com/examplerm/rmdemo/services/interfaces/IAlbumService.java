package com.examplerm.rmdemo.services.interfaces;


import org.springframework.web.multipart.MultipartFile;

import com.examplerm.rmdemo.controllers.dtos.request.CreateAlbumRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateAlbumRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Album;

public interface IAlbumService {

    BaseResponse get(Long id);

    BaseResponse get(String name);

    BaseResponse uploadPhoto(MultipartFile file);

    BaseResponse create(CreateAlbumRequest request);

    BaseResponse update(Long id, UpdateAlbumRequest request);

    BaseResponse list();

    void delete(Long id);
    
    Album findById(Long id);

    Album findByName(String name);

}
