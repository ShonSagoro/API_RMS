package com.examplerm.rmdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePlaylistLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.IPlaylistLibraryService;

@RestController
@RequestMapping("playlist-library")
public class PlaylistLibraryController {
    @Autowired
    private IPlaylistLibraryService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreatePlaylistLibraryRequest request){
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("playlists/library/{libraryId}")
    public ResponseEntity<BaseResponse> listAllPlaylistByIdLibrary(@PathVariable Long libraryId){
        BaseResponse baseResponse= service.listAllPlaylistByIdLibrary(libraryId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("playlist/{playlistId}/library/{libraryId}")
    public void deletePlaylistFromLibraryByThierIds(@PathVariable Long playlistId, @PathVariable Long libraryId ){
        service.deletePlaylistFromLibraryByThierIds(playlistId,libraryId);
    }
    
    @GetMapping("health")
    public String health() {
        return "Ok";
    }

}
