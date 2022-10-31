package com.examplerm.rmdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.IPodcastLibraryService;

@RestController
@RequestMapping("podcast-playlist")
public class PodcastLibraryController {
    @Autowired
    private IPodcastLibraryService service;

    @GetMapping("podcasts/playlist/{playlistId}")
    public ResponseEntity<BaseResponse> listAllPodcastsByIdLibrary(@PathVariable Long libraryId){
        BaseResponse baseResponse= service.listAllPodcastsByIdLibrary(libraryId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("podcasts/playlist/{playlistId}")
    public void deletePodcastsByIdLibrary(@PathVariable Long playlistId){
        service.deletePodcastsByIdLibrary(playlistId);
    }
}
