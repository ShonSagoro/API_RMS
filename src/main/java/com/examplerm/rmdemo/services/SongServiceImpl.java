package com.examplerm.rmdemo.services;

import com.examplerm.rmdemo.controllers.dtos.request.CreateSongRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateSongRequest;
import com.examplerm.rmdemo.controllers.dtos.response.AlbumResponse;
import com.examplerm.rmdemo.controllers.dtos.response.ArtistResponse;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetSongResponse;
import com.examplerm.rmdemo.entities.Album;
import com.examplerm.rmdemo.entities.Artist;
import com.examplerm.rmdemo.entities.Song;
import com.examplerm.rmdemo.repositories.ISongRepository;
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
public class SongServiceImpl implements ISongService {

    @Autowired
    private ISongRepository repository;

    @Autowired 
    private IArtistService artistService;

    @Autowired
    private IAlbumService albumService;
    
    @Autowired
    private IFileService fileService;

    @Override
    public BaseResponse get(Long id) {
        GetSongResponse response=from(id);

        return BaseResponse.builder()
                .data(response)
                .message("Song has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse get(String name) {
        GetSongResponse response=from(name);

        return BaseResponse.builder()
                .data(response)
                .message("Song has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateSongRequest request) {
        Song song = from(request);
        GetSongResponse response = from(repository.save(song));
        return BaseResponse.builder()
                .data(response)
                .message("Song created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse uploadSong(MultipartFile file){
        String songUrl= fileService.upload(file);
        return BaseResponse.builder()
                .data(songUrl)
                .message("The Song uploaded correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
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
    public BaseResponse list() {
        List<GetSongResponse> response= repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("Songs have been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateSongRequest request) {
        Song song = findById(id);
        song = update(song, request);
        GetSongResponse response=from(song);

        return BaseResponse.builder()
                .data(response)
                .message("Song has been updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);

    }

    private Song update(Song song, UpdateSongRequest request) {
        song.setName(request.getName());
        song.setDuration(request.getDuration());
        song.setPhotoUrl(request.getPhotoUrl());
        return repository.save(song);
    }

    private Song from(CreateSongRequest request) {
        Song song = new Song();
        song.setName(request.getName());
        song.setDuration(request.getDuration());
        song.setAlbum(albumService.findById(request.getAlbumId()));
        song.setArtist(artistService.findById(request.getArtistId()));
        song.setCreationDate(getDate());
        song.setSongUrl(request.getSongUrl());
        song.setPhotoUrl(request.getPhotoUrl());
        return song;
    }

    private GetSongResponse from(Song song) {
        GetSongResponse response = new GetSongResponse();
        response.setId(song.getId());
        response.setName(song.getName());
        response.setDuration(song.getDuration());
        response.setCreationDate(song.getCreationDate());
        response.setSongUrl(song.getSongUrl());
        response.setAlbum(from(song.getAlbum()));
        response.setArtist(from(song.getArtist()));
        response.setPhotoUrl(song.getPhotoUrl());
        return response;
    }

    private AlbumResponse from(Album album){
        AlbumResponse response= new AlbumResponse();
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
    
    private GetSongResponse from(Long idSong) {
        return repository.findById(idSong)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("The song does not exist"));
    }

    private GetSongResponse from(String name) {
        return repository.findByName(name)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("The song does not exist"));
    }

    public Song findById(Long idSong){
        return repository.findById(idSong)
        .orElseThrow(() -> new RuntimeException("The song does not exist"));
    }
    public Song findByName(String name){
        return repository.findByName(name)
        .orElseThrow(() -> new RuntimeException("The song does not exist"));
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
