package com.examplerm.rmdemo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePodcastLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetPodcastLibraryResponse;
import com.examplerm.rmdemo.controllers.dtos.response.LibraryResponse;
import com.examplerm.rmdemo.controllers.dtos.response.PodcastResponse;
import com.examplerm.rmdemo.entities.Library;
import com.examplerm.rmdemo.entities.Podcast;
import com.examplerm.rmdemo.entities.pivots.PodcastLibrary;
import com.examplerm.rmdemo.entities.projections.PodcastProjection;
import com.examplerm.rmdemo.repositories.IPodcastLibraryRepository;
import com.examplerm.rmdemo.services.interfaces.ILibraryService;
import com.examplerm.rmdemo.services.interfaces.IPodcastLibraryService;
import com.examplerm.rmdemo.services.interfaces.IPodcastService;

@Service
public class PodcastLibraryServiceImpl implements IPodcastLibraryService{

    @Autowired
    private IPodcastLibraryRepository repository;

    @Autowired 
    private IPodcastService podcastService;
    
    @Autowired
    private ILibraryService libraryService;

    @Override
    public BaseResponse create(CreatePodcastLibraryRequest request) {
        PodcastLibrary podcastLibrary = from(request);
        GetPodcastLibraryResponse response= from(repository.save(podcastLibrary));
        return BaseResponse.builder()
            .data(response)
            .message("Relation between Podcast and Library has been created correctly")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.CREATED).build();
    }

    private PodcastLibrary from(CreatePodcastLibraryRequest request){
        PodcastLibrary podcastLibrary= new PodcastLibrary();
        podcastLibrary.setLibrary(libraryService.findById(request.getIdLibrary()));
        podcastLibrary.setPodcast(podcastService.findById(request.getIdPodcast()));
        return podcastLibrary;
    }

    private GetPodcastLibraryResponse from(PodcastLibrary podcastLibrary){
        GetPodcastLibraryResponse response= new GetPodcastLibraryResponse();
        response.setId(podcastLibrary.getId());
        response.setLibrary(from(podcastLibrary.getLibrary()));
        response.setPodcast(from(podcastLibrary.getPodcast()));
        return response;
    }

    private PodcastResponse from(Podcast podcast){
        PodcastResponse response= new PodcastResponse();
        response.setId(podcast.getId());
        response.setName(podcast.getName());
        response.setCategory(podcast.getCategory());
        response.setDescription(podcast.getDescription());
        response.setCreationDate(podcast.getCreationDate());
        return response;
    } 

    private LibraryResponse from(Library library){
        LibraryResponse response= new LibraryResponse();
        response.setId(library.getId());
        return response;
    }

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

    private PodcastResponse from(PodcastProjection podcast){
        PodcastResponse response = new PodcastResponse();
        response.setId(podcast.getId());
        response.setName(podcast.getName());
        response.setCategory(podcast.getCategory());
        response.setDescription(podcast.getDescription());
        response.setCreationDate(podcast.getCreation_Date());
        return response;
    }

    @Override
    public void deletePodcastFromLibraryByThierIds(Long podcastId, Long libraryId) {
        repository.deletePodcastFromLibraryByThierIds(podcastId, libraryId);        
    }

    


    
}
