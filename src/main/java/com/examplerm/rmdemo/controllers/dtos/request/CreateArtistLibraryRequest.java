package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CreateArtistLibraryRequest {

    private Long idArtist;

    private Long idLibrary;

}
