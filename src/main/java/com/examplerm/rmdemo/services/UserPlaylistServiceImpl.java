package com.examplerm.rmdemo.services;

import com.examplerm.rmdemo.controllers.dtos.request.CreateUserPlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.*;
import com.examplerm.rmdemo.entities.*;
import com.examplerm.rmdemo.entities.pivots.UserPlaylist;
import com.examplerm.rmdemo.entities.projections.PlaylistProjection;
import com.examplerm.rmdemo.entities.projections.UserProjection;
import com.examplerm.rmdemo.repositories.IUserPlaylistRepository;
import com.examplerm.rmdemo.services.interfaces.IPlaylistService;
import com.examplerm.rmdemo.services.interfaces.IUserPlaylistService;
import com.examplerm.rmdemo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPlaylistServiceImpl implements IUserPlaylistService {

    @Autowired
    private IUserPlaylistRepository repository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPlaylistService playlistService;

    @Override
    public BaseResponse create(CreateUserPlaylistRequest request) {
        UserPlaylist userPlaylist= from(request);
        GetUserPlaylistResponse response = from(repository.save(userPlaylist));
        return BaseResponse.builder()
                .data(response)
                .message("Relation between User and Playlist has been created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    private GetUserPlaylistResponse from(UserPlaylist userPlaylist){
        GetUserPlaylistResponse response= new GetUserPlaylistResponse();
        response.setId(userPlaylist.getId());
        response.setPlaylist(from(userPlaylist.getPlaylist()));
        response.setUser(from(userPlaylist.getUser()));
        return response;

    }

    private UserPlaylist from(CreateUserPlaylistRequest request){
        UserPlaylist userPlaylist= new UserPlaylist();
        userPlaylist.setPlaylist(playlistService.findById(request.getIdPlaylist()));
        userPlaylist.setUser(userService.findById(request.getIdUser()));
        return userPlaylist;
    }

    private PlaylistResponse from(Playlist playlist){
        PlaylistResponse response= new PlaylistResponse();
        response.setId(playlist.getId());
        response.setName(playlist.getName());
        response.setCreationDate(playlist.getCreationDate());
        response.setDuration(playlist.getDuration());
        response.setDescription(playlist.getDescription());
        return response;
    }

    private UserResponse from(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setLibrary(from(user.getLibrary()));
        return response;
    }

    private Long from(Library library){
        return library.getId();
    }

    @Override
    public BaseResponse listAllPlaylistsByUserId(Long userId) {
        List<PlaylistProjection> playlist= repository.listAllPlaylistsByIdUser(userId);
        List<PlaylistResponse> response=playlist.stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Playlist list by user id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse listAllUsersByPlaylistId(Long playlistId) {
        List<UserProjection> user= repository.listAllUsersByIdPlaylist(playlistId);
        List<UserResponse> response=user.stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Playlist list by user id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void deletePlaylistFromUserByThierIds(Long playlistId, Long userId) {
        repository.deletePlaylistFromUserByThierIds(playlistId,userId);
    }

    @Override
    public void deletePlaylistsByIdUser(Long userId){
        repository.deletePlaylistsByIdUser(userId);
    }

    private PlaylistResponse from(PlaylistProjection playlist){
        PlaylistResponse response= new PlaylistResponse();
        response.setId(playlist.getId());
        response.setName(playlist.getName());
        response.setCreationDate(playlist.getCreation_Date());
        response.setDuration(playlist.getDuration());
        response.setDescription(playlist.getDescription());
        return response;
    }

    private UserResponse from(UserProjection user){
        UserResponse response= new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setLibrary(user.getLibrary_id());
        return response;
    }

}
