package com.examplerm.rmdemo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.PlaylistResponse;
import com.examplerm.rmdemo.entities.projections.PlaylistProjection;
import com.examplerm.rmdemo.repositories.IPlaylistLibraryRepository;
import com.examplerm.rmdemo.services.interfaces.IPlaylistLibraryService;

public class PlaylistLibraryServiceImpl implements IPlaylistLibraryService{

    @Autowired
    private IPlaylistLibraryRepository repository;
    
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
        response.setCategory(playlist.getCategory());
        response.setCreationDate(playlist.getCreationDate());
        response.setDescription(playlist.getDescription());
        return response;
    }
    
}
