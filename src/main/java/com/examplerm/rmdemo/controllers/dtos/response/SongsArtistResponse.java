package com.examplerm.rmdemo.controllers.dtos.response;

import com.examplerm.rmdemo.entities.Album;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class SongsArtistResponse {

    private Integer songId;

    private String songName;

    private String songDuration;

    private Album songAlbum;
}
