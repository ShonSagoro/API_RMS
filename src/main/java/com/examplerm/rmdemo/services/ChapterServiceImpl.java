package com.examplerm.rmdemo.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.examplerm.rmdemo.controllers.dtos.response.*;
import com.examplerm.rmdemo.entities.projections.ChapterProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.examplerm.rmdemo.controllers.dtos.request.CreateChapterRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateChapterRequest;
import com.examplerm.rmdemo.entities.Chapter;
import com.examplerm.rmdemo.entities.Podcast;
import com.examplerm.rmdemo.repositories.IChapterRepository;
import com.examplerm.rmdemo.services.interfaces.IChapterService;
import com.examplerm.rmdemo.services.interfaces.IFileService;
import com.examplerm.rmdemo.services.interfaces.IPodcastService;

@Service
public class ChapterServiceImpl implements IChapterService{
    
    @Autowired
    private IChapterRepository repository;

    @Autowired 
    private IPodcastService podcastService;

    @Autowired
    private IFileService fileService;

    @Override
    public BaseResponse create(CreateChapterRequest request) {
        Chapter chapter= from(request);
        GetChapterResponse response=from(repository.save(chapter));
        return BaseResponse.builder()
            .data(response)
            .message("Chapter has been create")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse uploadChapter(MultipartFile file){
        String chapterUrl=fileService.upload(file);
        return BaseResponse.builder()
            .data(chapterUrl)
            .message("The Chapter uploaded correctly")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BaseResponse update(Long id, UpdateChapterRequest request) {
        Chapter chapter=repository.findById(id)
            .orElseThrow(()-> new RuntimeException("The chapter does not exist"));
        chapter= update(chapter, request);
        GetChapterResponse response= from(chapter);

        return BaseResponse.builder()
            .data(response)
            .message("Chapter has been updated")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    public BaseResponse getChapters(Long id){
        List<ChapterProjection> response= repository.getChaptersByPodcastId(id);
        if (!response.isEmpty()){
            List<ChapterResponse> responseChapter= response.stream()
                    .map(this::from)
                    .collect(Collectors.toList());
            return BaseResponse.builder()
                    .data(responseChapter)
                    .message("Chapters by podcast id have been found")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK).build();
        }
        else{
            return BaseResponse.builder().message("This podcast doesn't have chapters").build();
        }
    }

    private ChapterResponse from(ChapterProjection chapterProjection) {
        ChapterResponse response= new ChapterResponse();
        response.setId(chapterProjection.getId());
        response.setTitle(chapterProjection.getTitle());
        response.setDuration(chapterProjection.getDuration());
        response.setCreationDate(chapterProjection.getCreation_Date());
        response.setPhotoUrl(chapterProjection.getPhoto_Url());
        response.setPodcast(from(podcastService.findById(chapterProjection.getPodcast_Id())));
        response.setDescription(chapterProjection.getDescription());
        response.setChapterUrl(chapterProjection.getChapter_Url());
        return response;
    }

    @Override
    public BaseResponse get(Long id) {
        GetChapterResponse response= from(id);
        return BaseResponse.builder()
            .data(response)
            .message("Chapter has been getted")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }
    @Override
    public BaseResponse get(String name) {
        GetChapterResponse response= from(name);
        return BaseResponse.builder()
            .data(response)
            .message("Chapter has been getted")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse list() {
        List<GetChapterResponse> response = repository.findAll()
            .stream()
            .map(this::from)
            .collect(Collectors.toList());

        return BaseResponse.builder()
            .data(response)
            .message("Chapters have been found")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    private Chapter update(Chapter chapter, UpdateChapterRequest request){
        chapter.setTitle(request.getTitle());
        chapter.setDescription(request.getDescription());
        chapter.setPhotoUrl(request.getPhotoUrl());
        return repository.save(chapter);
    }

    private GetChapterResponse from(Long id){
        return repository.findById(id).
                map(this::from)
                .orElseThrow(()-> new RuntimeException("The chapter does not exist"));
    }
    private GetChapterResponse from(String name){
        return repository.findByName(name).
                map(this::from)
                .orElseThrow(()-> new RuntimeException("The chapter does not exist"));
    }

    @Override
    public Chapter findById(Long id) {
        return repository.findById(id)
            .orElseThrow(()-> new RuntimeException("The chapter does not exist"));
    }
    @Override
    public Chapter findByName(String name) {
        return repository.findByName(name)
            .orElseThrow(()-> new RuntimeException("The chapter does not exist"));
    }

    private GetChapterResponse from(Chapter chapter){
        GetChapterResponse response= new GetChapterResponse();
        response.setId(chapter.getId());
        response.setTitle(chapter.getTitle());
        response.setDescription(chapter.getDescription());
        response.setDuration(chapter.getDuration());
        response.setChapterUrl(chapter.getChapterUrl());
        response.setCreationDate(chapter.getCreationDate());
        response.setPodcast(from(chapter.getPodcast()));
        response.setPhotoUrl(chapter.getPhotoUrl());
        return response;
    }

    private PodcastResponse from(Podcast podcast){
        PodcastResponse response= new PodcastResponse();
        response.setId(podcast.getId());
        response.setName(podcast.getName());
        response.setCategory(podcast.getCategory());
        response.setDescription(podcast.getDescription());
        response.setCreationDate(podcast.getCreationDate());
        response.setPhotoUrl(podcast.getPhotoUrl());
        return response;

    }

    private Chapter from(CreateChapterRequest request){
        Chapter chapter= new Chapter();
        chapter.setTitle(request.getTitle());
        chapter.setDescription(request.getDescription());
        chapter.setDuration(request.getDuration());
        chapter.setChapterUrl(request.getChapterUrl());
        chapter.setPhotoUrl(request.getPhotoUrl());
        chapter.setCreationDate(getDate());
        chapter.setPodcast(podcastService.findById(request.getPodcastId()));
        return chapter;
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
    public BaseResponse uploadPhoto(MultipartFile file) {
        String photoUrl=fileService.upload(file);
        return BaseResponse.builder()
            .data(photoUrl)
            .message("The photo uploaded correctly")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

}
