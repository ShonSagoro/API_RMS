package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetSongPlaylistResponse {

    private Long id;

    private SongResponse song;

    private PlaylistResponse playlist;
    
}
