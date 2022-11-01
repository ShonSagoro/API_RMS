package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class GetArtistLibraryResponse {

    private Long id;

    private ArtistResponse artist;

    private LibraryResponse library;

}
