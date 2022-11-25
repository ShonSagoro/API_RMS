package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter@Setter
public class CreateArtistLibraryRequest {

    @NonNull
    private Long idArtist;

    @NonNull
    private Long idLibrary;

}
