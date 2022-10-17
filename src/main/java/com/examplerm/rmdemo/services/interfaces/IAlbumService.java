package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreateAlbumRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateAlbumRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Album;

@Service
public interface IAlbumService {

    BaseResponse get(Long id);

    BaseResponse create(CreateAlbumRequest request);

    BaseResponse update(Long id, UpdateAlbumRequest request);

    BaseResponse list();

    Album findOneAndEnsureExist(Long id);

    void delete(Long id);

    Album save(Album album);
}
