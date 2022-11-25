package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter@Setter
public class CreateAlbumLibraryRequest {
    @NonNull
    private Long idAlbum;
    @NonNull
    private Long idLibrary;
}
