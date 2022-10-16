package com.examplerm.rmdemo.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreateChapterRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateChapterRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetChapterResponse;
import com.examplerm.rmdemo.entities.Chapter;
import com.examplerm.rmdemo.repositories.IChapterRepository;
import com.examplerm.rmdemo.services.interfaces.IChapterService;

@Service
public class ChapterSeriveImpl implements IChapterService{
    
    @Autowired
    private IChapterRepository repository;

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
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    public BaseResponse update(Long id, UpdateChapterRequest request) {
        Chapter chapter=repository.findById(id).orElseThrow(()-> new RuntimeException("The chapter does not exist"));
        chapter= update(chapter, request);
        GetChapterResponse response= from(chapter);

        return BaseResponse.builder()
            .data(response)
            .message("Chapter has been updated")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
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
        return repository.save(chapter);
    }

    private GetChapterResponse from(Long id){
        return repository.findById(id).
                map(this::from)
                .orElseThrow(()-> new RuntimeException("The chapter does not exist"));
    }

    private GetChapterResponse from(Chapter chapter){
        GetChapterResponse response= new GetChapterResponse();
        response.setId(chapter.getId());
        response.setTitle(chapter.getTitle());
        response.setDescription(chapter.getDescription());
        response.setDuration(chapter.getDuration());
        response.setDateCreation(chapter.getCreationDate());
        return response;
    }

    private Chapter from(CreateChapterRequest request){
        Chapter chapter= new Chapter();
        chapter.setTitle(request.getTitle());
        chapter.setDescription(request.getDescription());
        chapter.setDuration(request.getDuration());
        chapter.setCreationDate(getDate());
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
}
