package com.examplerm.rmdemo.services;

import java.util.List;
import java.util.stream.Collectors;

import com.examplerm.rmdemo.services.interfaces.IAlbumService;
import com.examplerm.rmdemo.services.interfaces.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.examplerm.rmdemo.controllers.dtos.request.CreateArtistRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateArtistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetArtistResponse;
import com.examplerm.rmdemo.entities.Artist;
import com.examplerm.rmdemo.repositories.IArtistRepository;
import com.examplerm.rmdemo.services.interfaces.IArtistService;
import com.examplerm.rmdemo.services.interfaces.IFileService;

@Service
public class ArtistServiceImpl implements IArtistService{

    @Autowired 
    private IArtistRepository repository;

    @Autowired

    private IFileService fileService;

    @Autowired
    private ISongService songService;

    @Autowired
    private IAlbumService albumService;


    @Override
    public BaseResponse create(CreateArtistRequest request) {
        Artist artist = from(request);
        GetArtistResponse response=from(repository.save(artist));
        return BaseResponse.builder()
            .data(response)
            .message("Artist has been created")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BaseResponse list() {
        List<GetArtistResponse> response = repository.findAll()
            .stream()
            .map(this::from)
            .collect(Collectors.toList());

        return BaseResponse.builder()
            .data(response)
            .message("Artist have been found")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }


    public BaseResponse SonglistbyId(Long id) {
        repository.findById(id).orElseThrow(()->new RuntimeException("The artist does not exist"));
        return songService.getSongs(id);

    }

    @Override
    public BaseResponse albumlistbyId(Long id) {
        repository.findById(id).orElseThrow(()-> new RuntimeException("The artist does not exist"));
        return albumService.getAlbums(id);
    }

    @Override
    public BaseResponse update(Long id, UpdateArtistRequest request) {
        Artist artist = repository.findById(id).orElseThrow(()-> new RuntimeException("The artist does not exist"));
        artist= update(artist, request);
        GetArtistResponse response= from(artist);
        return BaseResponse.builder()
            .data(response)
            .message("Artist has been updated")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetArtistResponse response=from(id);
        return BaseResponse.builder()
            .data(response)
            .message("Artist has been getted")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }
    
    @Override
    public BaseResponse get(String name) {
        GetArtistResponse response=from(name);
        return BaseResponse.builder()
            .data(response)
            .message("Artist has been getted")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    private Artist update(Artist artist, UpdateArtistRequest request){
        artist.setName(request.getName());
        artist.setListener(request.getListener());
        artist.setPhotoUrl(request.getPhotoUrl());
        return repository.save(artist);
    }

    private GetArtistResponse from(Long id){
        return repository.findById(id).
                map(this::from)
                .orElseThrow(()-> new RuntimeException("The Artist does not exist"));
    }
    private GetArtistResponse from(String name){
        return repository.findByName(name).
                map(this::from)
                .orElseThrow(()-> new RuntimeException("The Artist does not exist"));
    }

    private GetArtistResponse from(Artist artist){
        GetArtistResponse response= new GetArtistResponse();
        response.setId(artist.getId());
        response.setName(artist.getName());
        response.setListener(artist.getListener());
        response.setPhotoUrl(artist.getPhotoUrl());
        return response;
    }

    private Artist from(CreateArtistRequest request){
        Artist Artist= new Artist();
        Artist.setName(request.getName());
        Artist.setListener((Integer) 0);
        Artist.setPhotoUrl(request.getPhotoUrl());
        return Artist;
    }

    @Override
    public Artist findById(Long id) {
        return repository.findById(id)
            .orElseThrow(()-> new RuntimeException("The Artist does not exist"));
    }
    @Override
    public Artist findByName(String name) {
        return repository.findByName(name)
            .orElseThrow(()-> new RuntimeException("The Artist does not exist"));
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
