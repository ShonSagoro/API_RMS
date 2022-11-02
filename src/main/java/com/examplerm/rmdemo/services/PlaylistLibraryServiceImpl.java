package com.examplerm.rmdemo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePlaylistLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetPlaylistLibraryResponse;
import com.examplerm.rmdemo.controllers.dtos.response.LibraryResponse;
import com.examplerm.rmdemo.controllers.dtos.response.PlaylistResponse;
import com.examplerm.rmdemo.entities.Library;
import com.examplerm.rmdemo.entities.Playlist;
import com.examplerm.rmdemo.entities.pivots.PlaylistLibrary;
import com.examplerm.rmdemo.entities.projections.PlaylistProjection;
import com.examplerm.rmdemo.repositories.IPlaylistLibraryRepository;
import com.examplerm.rmdemo.services.interfaces.ILibraryService;
import com.examplerm.rmdemo.services.interfaces.IPlaylistLibraryService;
import com.examplerm.rmdemo.services.interfaces.IPlaylistService;

@Service
public class PlaylistLibraryServiceImpl implements IPlaylistLibraryService{

    @Autowired
    private IPlaylistLibraryRepository repository;

    @Autowired 
    private IPlaylistService playlistService;

    @Autowired
    private ILibraryService libraryService;

    @Override
    public BaseResponse create(CreatePlaylistLibraryRequest request) {
        PlaylistLibrary playlistLibrary= from(request);
        GetPlaylistLibraryResponse response= from(repository.save(playlistLibrary));
        return BaseResponse.builder()
            .data(response)
            .message("Relation between Playlist and Library has been created correctly")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.CREATED).build();
    }

    private PlaylistLibrary from(CreatePlaylistLibraryRequest request){
        PlaylistLibrary playlistLibrary= new PlaylistLibrary();
        playlistLibrary.setLibrary(libraryService.findById(request.getIdLibrary()));
        playlistLibrary.setPlaylist(playlistService.findById(request.getIdPlaylist()));
        return playlistLibrary;
    }

    private GetPlaylistLibraryResponse from(PlaylistLibrary playlistLibrary){
        GetPlaylistLibraryResponse response= new GetPlaylistLibraryResponse();
        response.setId(playlistLibrary.getId());
        response.setLibrary(from(playlistLibrary.getLibrary()));
        response.setPlaylist(from(playlistLibrary.getPlaylist()));
        return response;
    }

    private PlaylistResponse from(Playlist playlist){
        PlaylistResponse response= new PlaylistResponse();
        response.setId(playlist.getId());
        response.setName(playlist.getName());
        response.setCreationDate(playlist.getCreationDate());
        response.setDescription(playlist.getDescription());
        return response;

    }

    private LibraryResponse from(Library library){
        LibraryResponse response= new LibraryResponse();
        response.setId(library.getId());
        return response;
    }

    @Override
    public BaseResponse listAllPlaylistByIdLibrary(Long librarytId) {
        List<PlaylistProjection> playlists= repository.listAllPlaylistByLibraryId(librarytId);
        List<PlaylistResponse> response= playlists.stream()
            .map(this::from)
            .collect(Collectors.toList());
        return BaseResponse.builder()
            .data(response)
            .message("Playlist list by library id")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void deletePlaylistsByIdLibrary(Long libraryId) {
        repository.deletePlaylistsByIdLibrary(libraryId);        
    }

    private PlaylistResponse from(PlaylistProjection playlist){
        PlaylistResponse response= new PlaylistResponse();
        response.setId(playlist.getId());
        response.setName(playlist.getName());
        response.setDuration(playlist.getDuration());
        response.setCreationDate(playlist.getDate_Creation());
        response.setDescription(playlist.getDescription());
        return response;
    }

}
