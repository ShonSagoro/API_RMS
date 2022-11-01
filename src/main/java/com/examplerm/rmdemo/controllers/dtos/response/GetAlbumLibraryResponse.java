package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class GetAlbumLibraryResponse {

    private Long id;

    private AlbumResponse album;

    private LibraryResponse library;

}
