package com.examplerm.rmdemo.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetPlaylistResponse;
import com.examplerm.rmdemo.entities.Playlist;
import com.examplerm.rmdemo.repositories.IPlaylistRepository;
import com.examplerm.rmdemo.services.interfaces.IFileService;
import com.examplerm.rmdemo.services.interfaces.IPlaylistService;
import com.examplerm.rmdemo.controllers.dtos.request.UpdatePlaylistRequest;

@Service
public class PlaylistServiceImpl implements IPlaylistService{

    @Autowired
    private IPlaylistRepository repository;

    @Autowired
    private IFileService fileService;

    @Override
    public BaseResponse create(CreatePlaylistRequest request) {
        Playlist playlist= from(request);
        GetPlaylistResponse response= from(repository.save(playlist)); 
        return BaseResponse.builder()
            .data(response)
            .message("Playlist has been created")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
  
    @Override
    public BaseResponse update(Long id, UpdatePlaylistRequest request) {
        Playlist playlist=repository.findById(id).orElseThrow(()->new RuntimeException("The playlist does not exist"));
        playlist= update(playlist, request);
        GetPlaylistResponse response=from(playlist);
        
        return BaseResponse.builder()
            .data(response)
            .message("Playlist has been updated")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK)
            .build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetPlaylistResponse response=from(id);
        return BaseResponse.builder()
            .data(response)
            .message("Playlist has been getted")
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse list() {
        List <GetPlaylistResponse>response= repository.findAll()
            .stream()
            .map(this::from)
            .collect(Collectors.toList());

        return BaseResponse.builder()
            .data(response)
            .message("Playlists have been found")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    private Playlist update(Playlist playlist, UpdatePlaylistRequest request){
        playlist.setName(request.getName());
        playlist.setDescription(request.getDescription());
        playlist.setPhotoUrl(request.getPhotoUrl());
        return playlist;

    }

    private Playlist from(CreatePlaylistRequest request){
        Playlist playlist=new Playlist();
        playlist.setName(request.getName());
        playlist.setDescription(request.getDescription());
        playlist.setDuration(request.getDuration());
        playlist.setCreationDate(getDate());
        playlist.setPhotoUrl(request.getPhotoUrl());
        return playlist;

    }

    private GetPlaylistResponse from(Playlist playlist){
        GetPlaylistResponse response= new GetPlaylistResponse();
        response.setId(playlist.getId());
        response.setName(playlist.getName());
        response.setCreationDate(playlist.getCreationDate());
        response.setDescription(playlist.getDescription());
        response.setDuration(playlist.getDuration());
        response.setPhotoUrl(playlist.getPhotoUrl());
        return response;
    }

    private GetPlaylistResponse from(Long id){
        return repository.findById(id)
            .map(this::from)
            .orElseThrow(()->new RuntimeException("The playlist does not exist"));
    }

    private String getDate(){
        LocalDateTime dateNow=LocalDateTime.now();
        String date=dateNow.format(getFormat());
        return date;
    }
    
    private DateTimeFormatter getFormat(){
        DateTimeFormatter format= DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return format;
    }

    @Override
    public Playlist findById(Long id) {
        return repository.findById(id)
        .orElseThrow(()->new RuntimeException("The playlist does not exist"));
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
