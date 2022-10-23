package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePlaylistLibraryRequest {

    private Long idPlaylist;

    private Long idLibrary;

}
