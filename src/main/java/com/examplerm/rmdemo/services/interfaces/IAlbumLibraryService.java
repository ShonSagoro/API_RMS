package com.examplerm.rmdemo.services.interfaces;

import com.examplerm.rmdemo.controllers.dtos.request.CreateAlbumLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

public interface IAlbumLibraryService {

    BaseResponse create(CreateAlbumLibraryRequest request);

    BaseResponse listAllAlbumsByIdLibrary(Long libraryId);

    void deleteAlbumsByIdFromLibraryId(Long albumId, Long libraryId);

}
