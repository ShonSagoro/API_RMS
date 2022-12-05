package com.examplerm.rmdemo.services;

import com.examplerm.rmdemo.controllers.dtos.request.CreateAlbumRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateAlbumRequest;
import com.examplerm.rmdemo.controllers.dtos.response.*;
import com.examplerm.rmdemo.entities.Album;
import com.examplerm.rmdemo.entities.Artist;
import com.examplerm.rmdemo.entities.projections.AlbumProjection;
import com.examplerm.rmdemo.entities.projections.SongProjection;
import com.examplerm.rmdemo.repositories.IAlbumRepository;
import com.examplerm.rmdemo.services.interfaces.IAlbumService;
import com.examplerm.rmdemo.services.interfaces.IArtistService;
import com.examplerm.rmdemo.services.interfaces.IFileService;

import com.examplerm.rmdemo.services.interfaces.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements IAlbumService {

    @Autowired
    private IAlbumRepository repository;

    @Autowired 
    private IArtistService artistService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private ISongService songService;

    @Override
    public BaseResponse get(Long id) {
        GetAlbumResponse response=from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Album has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }
    
    @Override
    public BaseResponse get(String name) {
        GetAlbumResponse response=from(name);
        return BaseResponse.builder()
                .data(response)
                .message("Album has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }
    
    @Override
    public BaseResponse create(CreateAlbumRequest request) {
        Album album = from(request);
        GetAlbumResponse response= from(repository.save(album));
        return BaseResponse.builder()
                .data(response)
                .message("Album created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateAlbumRequest request) {
        Album album = repository.findById(id)
            .orElseThrow(()-> new RuntimeException("The Album does not exist"));
        album = update(album, request);
        GetAlbumResponse response=from(album);
        return BaseResponse.builder()
                .data(response)
                .message("Album has been updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse list() {
        List<GetAlbumResponse> response= repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("Albums have been getted")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Album update(Album album, UpdateAlbumRequest request) {
        album.setName(request.getName());
        album.setDescription(request.getDescription());
        album.setDuration(request.getDuration());
        album.setPhotoUrl(request.getPhotoUrl());
        return repository.save(album);
    }

    private Album from(CreateAlbumRequest request) {
        Album album = new Album();
        album.setName(request.getName());
        album.setDescription(request.getDescription());
        album.setCreationDate(getDate());
        album.setDuration(request.getDuration());
        album.setPhotoUrl(request.getPhotoUrl());
        album.setArtist(artistService.findById(request.getIdArtist()));
        return repository.save(album);
    }

    private GetAlbumResponse from(Album album) {
        GetAlbumResponse response = new GetAlbumResponse();
        response.setId(album.getId());
        response.setName(album.getName());
        response.setDuration(album.getDuration());
        response.setCreationDate(album.getCreationDate());
        response.setDescription(album.getDescription());
        response.setArtist(from(album.getArtist()));
        response.setPhotoUrl(album.getPhotoUrl());
        return response;
    }

    private ArtistResponse from(Artist artist){
        ArtistResponse response= new ArtistResponse();
        response.setId(artist.getId());
        response.setName(artist.getName());
        response.setListener(artist.getListener());
        response.setPhotoUrl(artist.getPhotoUrl());
        return response;
    }

    private GetAlbumResponse from(Long idAlbum) {
        return repository.findById(idAlbum)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("The album does not exist"));
    }

    private GetAlbumResponse from(String nameAlbum) {
        return repository.findByName(nameAlbum)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("The album does not exist"));
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

    @Override
    public Album findByName(String name) {
        return repository.findByName(name)
            .orElseThrow(()-> new RuntimeException("The album does not exist"));
    }

    @Override
    public BaseResponse getAlbums(Long id) {
        List<AlbumProjection> response= repository.getAlbumsByArtistId(id);
        if (!response.isEmpty()){
            List<AlbumResponse> responseAlbum= response.stream()
                    .map(this::from)
                    .collect(Collectors.toList());
            return BaseResponse.builder()
                    .data(responseAlbum)
                    .message("Albums by artist id have been found")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK).build();
        }
        else{
            return BaseResponse.builder().message("This artist doesn't have songs").build();
        }
    }

    @Override
    public BaseResponse getSongsByAlbumId(Long id) {
        repository.findById(id).orElseThrow(()->new RuntimeException("The Album does not exist"));
        return songService.getSongsbyAlbumId(id);
    }

    private AlbumResponse from(AlbumProjection albumProjection) {
        AlbumResponse response= new AlbumResponse();
        response.setId(albumProjection.getId());
        response.setName(albumProjection.getName());
        response.setDuration(albumProjection.getDuration());
        response.setCreationDate(albumProjection.getCreation_Date());
        response.setPhotoUrl(albumProjection.getPhoto_Url());
        response.setArtist(from(artistService.findById(albumProjection.getArtist_Id())));
        response.setDescription(albumProjection.getDescription());
        return response;
    }


    @Override
    public Album findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The album does not exist"));
    }
}
