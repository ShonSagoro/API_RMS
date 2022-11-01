package com.examplerm.rmdemo.services;

import com.examplerm.rmdemo.controllers.dtos.request.CreateArtistLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.*;
import com.examplerm.rmdemo.entities.Artist;
import com.examplerm.rmdemo.entities.Library;
import com.examplerm.rmdemo.entities.pivots.ArtistLibrary;
import com.examplerm.rmdemo.entities.projections.ArtistProjection;
import com.examplerm.rmdemo.repositories.IArtistLibraryRepository;
import com.examplerm.rmdemo.services.interfaces.IArtistLibraryService;
import com.examplerm.rmdemo.services.interfaces.IArtistService;
import com.examplerm.rmdemo.services.interfaces.ILibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistLibraryImpl implements IArtistLibraryService {

    @Autowired
    private IArtistLibraryRepository repository;

    @Autowired
    private IArtistService artistService;

    @Autowired
    private ILibraryService libraryService;

    @Override
    public BaseResponse create(CreateArtistLibraryRequest request) {
        ArtistLibrary artistLibrary= from(request);
        GetArtistLibraryResponse response = from(repository.save(artistLibrary));
        return BaseResponse.builder()
                .data(response)
                .message("Relation between artist and library has been created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    private GetArtistLibraryResponse from(ArtistLibrary artistLibrary){
        GetArtistLibraryResponse response= new GetArtistLibraryResponse();
        response.setId(artistLibrary.getId());
        response.setArtist(from(artistLibrary.getArtist()));
        response.setLibrary(from(artistLibrary.getLibrary()));
        return response;

    }

    private ArtistLibrary from(CreateArtistLibraryRequest request){
        ArtistLibrary artistLibrary= new ArtistLibrary();
        artistLibrary.setArtist(artistService.findById(request.getIdArtist()));
        artistLibrary.setLibrary(libraryService.findById(request.getIdLibrary()));
        return artistLibrary;
    }

    private ArtistResponse from(Artist artist){
        ArtistResponse response= new ArtistResponse();
        response.setId(artist.getId());
        response.setName(artist.getName());
        response.setListener(artist.getListener());
        return response;
    }

    private LibraryResponse from(Library library){
        LibraryResponse response = new LibraryResponse();
        response.setId(library.getId());

        return response;
    }

    @Override
    public BaseResponse listAllArtistsByIdLibrary(Long libraryId) {
        List<ArtistProjection> artist= repository.listAllArtistsByIdLibrary(libraryId);
        List<ArtistResponse> response=artist.stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Artist list by libraryId")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void deleteArtistByIdFromLibraryId(Long artistId, Long libraryId) {
        repository.deleteArtistbyIdUserId(artistId,libraryId);
    }

    private ArtistResponse from(ArtistProjection artist){
        ArtistResponse response= new ArtistResponse();
        response.setId(artist.getId());
        response.setName(artist.getName());
        return response;
    }

}
