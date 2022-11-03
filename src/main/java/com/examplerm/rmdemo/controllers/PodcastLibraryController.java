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

import com.examplerm.rmdemo.controllers.dtos.request.CreatePodcastLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.IPodcastLibraryService;

@RestController
@RequestMapping("podcast-library")
public class PodcastLibraryController {

    @Autowired
    private IPodcastLibraryService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreatePodcastLibraryRequest request){
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("podcasts/library/{libraryId}")
    public ResponseEntity<BaseResponse> listAllPodcastsByIdLibrary(@PathVariable Long libraryId){
        BaseResponse baseResponse= service.listAllPodcastsByIdLibrary(libraryId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("podcast/{podcastId}/library/{libraryId}")
    public void deletePodcastFromLibraryByThierIds(@PathVariable Long podcastId,@PathVariable Long libraryId){
        service.deletePodcastFromLibraryByThierIds(podcastId, libraryId);
    }

}
