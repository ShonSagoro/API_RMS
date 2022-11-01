package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetPlaylistLibraryResponse {

    private Long id;

    private PlaylistResponse playlist;

    private LibraryResponse library;

}
