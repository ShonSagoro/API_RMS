package com.examplerm.rmdemo.controllers.dtos.response;

import java.util.List;

import com.examplerm.rmdemo.entities.Album;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class GetArtistResponse {
        
    private Long id;

    private String name;

    private Integer listener;
    
    private List<Album> albums;

}
