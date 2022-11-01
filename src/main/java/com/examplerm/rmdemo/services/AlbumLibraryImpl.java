package com.examplerm.rmdemo.services;

import com.examplerm.rmdemo.controllers.dtos.request.CreateAlbumLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.*;
import com.examplerm.rmdemo.entities.Album;
import com.examplerm.rmdemo.entities.Artist;
import com.examplerm.rmdemo.entities.Library;
import com.examplerm.rmdemo.entities.pivots.AlbumLibrary;
import com.examplerm.rmdemo.entities.projections.AlbumProjection;
import com.examplerm.rmdemo.repositories.IAlbumLibraryRepository;
import com.examplerm.rmdemo.services.interfaces.IAlbumLibraryService;
import com.examplerm.rmdemo.services.interfaces.IAlbumService;
import com.examplerm.rmdemo.services.interfaces.ILibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumLibraryImpl implements IAlbumLibraryService {

    @Autowired
    private IAlbumLibraryRepository repository;

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private ILibraryService libraryService;

    @Override
    public BaseResponse create(CreateAlbumLibraryRequest request) {
        AlbumLibrary albumLibrary = from(request);
        GetAlbumLibraryResponse response= from(repository.save(albumLibrary));
        return BaseResponse.builder()
                .data(response)
                .message("Relation between Album and Library has been created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    private GetAlbumLibraryResponse from(AlbumLibrary albumLibrary){
        GetAlbumLibraryResponse response= new GetAlbumLibraryResponse();
        response.setId(albumLibrary.getId());
        response.setAlbum(from(albumLibrary.getAlbum()));
        response.setLibrary(from(albumLibrary.getLibrary()));
        return response;

    }

    private AlbumLibrary from(CreateAlbumLibraryRequest request){
        AlbumLibrary albumLibrary= new AlbumLibrary();
        albumLibrary.setAlbum(albumService.findById(request.getIdAlbum()));
        albumLibrary.setLibrary(libraryService.findById(request.getIdLibrary()));
        return albumLibrary;
    }

    private AlbumResponse from(Album album){
        AlbumResponse response= new AlbumResponse();
        response.setId(album.getId());
        response.setName(album.getName());
        response.setArtist(from(album.getArtist()));
        response.setDescription(album.getDescription());
        response.setDuration(album.getDuration());
        response.setCreationDate(album.getCreationDate());
        return response;
    }

    private ArtistResponse from(Artist artist){
        ArtistResponse response= new ArtistResponse();
        response.setListener(artist.getListener());
        response.setName(artist.getName());
        response.setId(artist.getId());
        return response;
    }

    private LibraryResponse from(Library library){
        LibraryResponse response = new LibraryResponse();
        response.setId(library.getId());
        return response;
    }

    @Override
    public BaseResponse listAllAlbumsByIdLibrary(Long libraryId) {
        List<AlbumProjection> album= repository.listAllAlbumsByLibraryId(libraryId);
        List<AlbumResponse> response=album.stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Artist list by library id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void deleteAlbumsByIdFromLibraryId(Long albumId, Long libraryId) {
        repository.deleteAlbumsByIdFromLibraryId(albumId,libraryId);
    }

    private AlbumResponse from(AlbumProjection album){
        AlbumResponse response= new AlbumResponse();
        response.setId(album.getId());
        response.setName(album.getName());
        response.setCreationDate(album.getCreationDate());
        response.setDuration(album.getDuration());
        response.setArtist(from(album.getArtist()));
        return response;
    }

}
