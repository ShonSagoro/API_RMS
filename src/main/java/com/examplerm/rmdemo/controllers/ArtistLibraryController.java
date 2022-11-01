package com.examplerm.rmdemo.controllers;

import com.examplerm.rmdemo.controllers.dtos.request.CreateArtistLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.IArtistLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("artist-library")
public class ArtistLibraryController {

    @Autowired
    private IArtistLibraryService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateArtistLibraryRequest request){
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("artist/library/{libraryId}")
    public ResponseEntity<BaseResponse> listAllArtistByIdLibrary(@PathVariable Long libraryId){
        BaseResponse baseResponse= service.listAllArtistsByIdLibrary(libraryId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("artists/{artistId}/library/{libraryId}")
    public void deleteArtistByIdfromLibraryId(@PathVariable Long artistId,@PathVariable Long libraryId){
        service.deleteArtistByIdFromLibraryId(artistId,libraryId);
    }

}
