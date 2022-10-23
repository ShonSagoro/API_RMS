package com.examplerm.rmdemo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.PodcastResponse;
import com.examplerm.rmdemo.entities.projections.PodcastProjection;
import com.examplerm.rmdemo.repositories.IPodcastLibraryRepository;
import com.examplerm.rmdemo.services.interfaces.IPodcastLibraryService;

public class PodcastLibraryServiceImpl implements IPodcastLibraryService{

    @Autowired
    private IPodcastLibraryRepository repository;

    @Override
    public BaseResponse listAllPodcastsByIdLibrary(Long libraryId) {
        List<PodcastProjection> podcasts = repository.listAllPodcastByLibraryId(libraryId);
        List<PodcastResponse> response= podcasts.stream()
            .map(this::from)
            .collect(Collectors.toList());

        return BaseResponse.builder()
            .data(response)
            .message("Podcast list by library id")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void deletePodcastsByIdLibrary(Long libraryId) {
        repository.deletePodcastsByIdLibrary(libraryId);
    }

    private PodcastResponse from(PodcastProjection podcast){
        PodcastResponse response = new PodcastResponse();
        response.setId(podcast.getId());
        response.setName(podcast.getName());
        response.setCategory(podcast.getCategory());
        response.setDescription(podcast.getDescription());
        response.setCreationDate(podcast.getCreationDate());
        return response;
    }



    
}
