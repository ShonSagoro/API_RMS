package com.examplerm.rmdemo.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.examplerm.rmdemo.services.interfaces.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePodcastRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdatePodcastRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetPodcastResponse;
import com.examplerm.rmdemo.entities.Podcast;
import com.examplerm.rmdemo.repositories.IPodcastRepository;
import com.examplerm.rmdemo.services.interfaces.IFileService;
import com.examplerm.rmdemo.services.interfaces.IPodcastService;

@Service
public class PodcastServiceImpl implements IPodcastService{

    @Autowired
    private IPodcastRepository repository;

    @Autowired
    private IChapterService chapterService;

    @Autowired
    private IFileService fileService;
    
    @Override
    public BaseResponse create(CreatePodcastRequest request) {
        Podcast podcast= from(request);
        GetPodcastResponse response=from(repository.save(podcast));
        return BaseResponse.builder()
            .data(response)
            .message("Podcast has been create")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    public BaseResponse chaptersByPodcastId(Long id){
        repository.findById(id).orElseThrow(()->new RuntimeException("The podcast does not exist"));
        return chapterService.getChapters(id);
    }

    @Override
    public BaseResponse update(Long id, UpdatePodcastRequest request) {
        Podcast podcast=repository.findById(id).orElseThrow(()-> new RuntimeException("The podcast does not exist"));
        podcast= update(podcast, request);
        GetPodcastResponse response= from(podcast);

        return BaseResponse.builder()
            .data(response)
            .message("Podcast has been updated")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetPodcastResponse response= from(id);
        return BaseResponse.builder()
            .data(response)
            .message("Podcast has been getted")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(String name) {
        GetPodcastResponse response= from(name);
        return BaseResponse.builder()
            .data(response)
            .message("Podcast has been getted")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse list() {
        List<GetPodcastResponse> response = repository.findAll()
            .stream()
            .map(this::from)
            .collect(Collectors.toList());

        return BaseResponse.builder()
            .data(response)
            .message("Podcast has been updated")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    private Podcast update(Podcast podcast, UpdatePodcastRequest request){
        podcast.setName(request.getName());
        podcast.setDescription(request.getDescription());
        podcast.setCategory(request.getCategory());
        podcast.setPhotoUrl(request.getPhotoUrl());
        return repository.save(podcast);
    }

    private GetPodcastResponse from(Long id){
        return repository.findById(id).
                map(this::from)
                .orElseThrow(()-> new RuntimeException("The podcast does not exist"));
    }

    private GetPodcastResponse from(String name){
        return repository.findByName(name).
                map(this::from)
                .orElseThrow(()-> new RuntimeException("The podcast does not exist"));
    }

    private GetPodcastResponse from(Podcast podcast){
        GetPodcastResponse response= new GetPodcastResponse();
        response.setId(podcast.getId());
        response.setName(podcast.getName());
        response.setDescription(podcast.getDescription());
        response.setCategory(podcast.getCategory());
        response.setCreationDate(podcast.getCreationDate());
        response.setPhotoUrl(podcast.getPhotoUrl());
        return response;
    }

    private Podcast from(CreatePodcastRequest request){
        Podcast podcast= new Podcast();
        podcast.setName(request.getName());
        podcast.setDescription(request.getDescription());
        podcast.setCategory(request.getCategory());
        podcast.setCreationDate(getDate());
        podcast.setPhotoUrl(request.getPhotoUrl());
        return podcast;
    }

    private String getDate(){
        LocalDateTime dateNow= LocalDateTime.now();
        String date=dateNow.format(getFormat());
        return date;
    }

    private DateTimeFormatter getFormat(){
        DateTimeFormatter format= DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return format;
    }

    @Override
    public Podcast findById(Long id) {
        return repository.findById(id)
         .orElseThrow(()-> new RuntimeException("The podcast does not exist"));
    }
    @Override
    public Podcast findByName(String name) {
        return repository.findByName(name)
         .orElseThrow(()-> new RuntimeException("The podcast does not exist"));
    }

    @Override
    public BaseResponse uploadPhoto(MultipartFile file) {
        String photoUrl=fileService.upload(file);
        return BaseResponse.builder()
            .data(photoUrl)
            .message("The photo uploaded correctly")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }
    
}