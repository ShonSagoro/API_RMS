package com.examplerm.rmdemo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreateArtistRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateArtistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetArtistResponse;
import com.examplerm.rmdemo.entities.Artist;
import com.examplerm.rmdemo.repositories.IArtistRepository;
import com.examplerm.rmdemo.services.interfaces.IArtistService;

@Service
public class ArtistServiceImpl implements IArtistService{
    @Autowired 
    private IArtistRepository repository;


    @Override
    public BaseResponse create(CreateArtistRequest request) {
        Artist artist = from(request);
        GetArtistResponse response=from(repository.save(artist));
        return BaseResponse.builder()
            .data(response)
            .message("Artist has been create")
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

    private Artist update(Artist artist, UpdateArtistRequest request){
        artist.setName(request.getName());
        artist.setListener(request.getListener());
        return repository.save(artist);
    }

    private GetArtistResponse from(Long id){
        return repository.findById(id).
                map(this::from)
                .orElseThrow(()-> new RuntimeException("The Artist does not exist"));
    }

    private GetArtistResponse from(Artist artist){
        GetArtistResponse response= new GetArtistResponse();
        response.setId(artist.getId());
        response.setName(artist.getName());
        response.setAlbums(null);
        return response;
    }

    private Artist from(CreateArtistRequest request){
        Artist Artist= new Artist();
        Artist.setName(request.getName());
        Artist.setListener(0);
        return Artist;
    }
    
    
}
