package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;


@Service
public interface IPlaylistLibraryService {
        //http://localhost:8080/user/id_user/id_library/id_playlist
        BaseResponse listAllPlaylistByIdLibrary(Long librarytId);
    
        void deletePlaylistsByIdLibrary(Long libraryId);
}
