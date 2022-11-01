package com.examplerm.rmdemo.services.interfaces;


import com.examplerm.rmdemo.controllers.dtos.request.CreatePlaylistLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;


public interface IPlaylistLibraryService {

        BaseResponse create(CreatePlaylistLibraryRequest request);

        BaseResponse listAllPlaylistByIdLibrary(Long librarytId);
    
        void deletePlaylistsByIdLibrary(Long libraryId);

        
}
