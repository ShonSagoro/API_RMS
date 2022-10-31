package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePlaylistLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;


@Service
public interface IPlaylistLibraryService {

        BaseResponse create(CreatePlaylistLibraryRequest request);

        BaseResponse listAllPlaylistByIdLibrary(Long librarytId);
    
        void deletePlaylistsByIdLibrary(Long libraryId);

        
}
