package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateSongPlaylistRequest {

    private Long idSong;

    private Long idPlaylist;

}
