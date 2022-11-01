package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class GetUserPlaylistResponse {

    private Long id;

    private UserResponse user;

    private PlaylistResponse playlist;

}
