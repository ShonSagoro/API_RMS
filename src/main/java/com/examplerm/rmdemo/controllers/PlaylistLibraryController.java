package com.examplerm.rmdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.IPlaylistLibraryService;

@RestController
@RequestMapping("playlist-library")
public class PlaylistLibraryController {
    @Autowired
    private IPlaylistLibraryService service;

    @GetMapping("playlists/library/{librarytId}")
    public ResponseEntity<BaseResponse> listAllPlaylistByIdLibrary(@PathVariable Long librarytId){
        BaseResponse baseResponse= service.listAllPlaylistByIdLibrary(librarytId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("playlists/library/{librarytId}")
    public void deletePlaylistsByIdLibrary(@PathVariable Long librarytId ){
        service.deletePlaylistsByIdLibrary(librarytId);
    }
}
