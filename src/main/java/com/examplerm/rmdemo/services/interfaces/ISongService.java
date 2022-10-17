package com.examplerm.rmdemo.services.interfaces;

import com.examplerm.rmdemo.controllers.dtos.request.CreateSongRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateSongRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Song;
import org.springframework.stereotype.Service;

@Service
public interface ISongService {
    BaseResponse get(Long id);

    BaseResponse create(CreateSongRequest request);

    BaseResponse list();

    BaseResponse update(Long id, UpdateSongRequest request);

    Song findOneAndEnsureExist(Long id);

    void delete(Long id);

    Song save(Song song);
}
