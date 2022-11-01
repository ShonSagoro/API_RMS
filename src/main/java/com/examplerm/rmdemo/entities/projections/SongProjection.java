package com.examplerm.rmdemo.entities.projections;

import com.examplerm.rmdemo.entities.Album;
import com.examplerm.rmdemo.entities.Artist;

public interface SongProjection {

    Long getId();

    String getName();

    String getDuration();
    
    String getCreationDate();

    String getSongUrl();

    Artist getArtist();
    
    Album getAlbum();

}
