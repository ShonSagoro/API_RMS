package com.examplerm.rmdemo.controllers;

import com.examplerm.rmdemo.controllers.dtos.request.CreateAlbumLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.IAlbumLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("album-library")
public class AlbumLibraryController {

    @Autowired
    private IAlbumLibraryService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateAlbumLibraryRequest request){
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("albums/library/{libraryId}")
    public ResponseEntity<BaseResponse> listAllAlbumsByIdLibrary(@PathVariable Long libraryId){
        BaseResponse baseResponse= service.listAllAlbumsByIdLibrary(libraryId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("album/{albumId}/library/{libraryId}")
    public void deleteAlbumFromLibraryByThierIds(@PathVariable Long albumId, @PathVariable Long libraryId){
        service.deleteAlbumFromLibraryByThierIds(albumId, libraryId);
    }

}
