package com.examplerm.rmdemo.entities.projections;

import com.examplerm.rmdemo.entities.Artist;

public interface AlbumProjection {

    Long getId();

    String getName();

    String getDuration();

    String getDescription();

    String getCreationDate();

    Artist getArtist();
}
