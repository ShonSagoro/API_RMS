package com.examplerm.rmdemo.services.interfaces;

import com.examplerm.rmdemo.controllers.dtos.request.CreateArtistLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;


public interface IArtistLibraryService {

    BaseResponse create(CreateArtistLibraryRequest request);

    BaseResponse listAllArtistsByIdLibrary(Long libraryId);

    void deleteArtistByIdFromLibraryId(Long artistId, Long libraryId);

}
