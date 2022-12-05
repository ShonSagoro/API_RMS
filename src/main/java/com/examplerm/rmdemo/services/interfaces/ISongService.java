package com.examplerm.rmdemo.services.interfaces;

import com.examplerm.rmdemo.controllers.dtos.request.CreateSongRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateSongRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Song;
import org.springframework.web.multipart.MultipartFile;

public interface ISongService {

    BaseResponse get(Long id);

    BaseResponse getByAlbumId(Long id);
    
    BaseResponse getByArtistId(Long id);

    BaseResponse get(String name);

    BaseResponse create(CreateSongRequest request);

    BaseResponse uploadPhoto(MultipartFile file);

    BaseResponse uploadSong(MultipartFile file);

    BaseResponse list();
    
    BaseResponse update(Long id, UpdateSongRequest request);
    
    void delete(Long id);
    
    Song findById(Long id);

    Song findByName(String name);

    Song findByArtistId(Long artistId);

    Song findByAlbumId(Long albumId);
}
