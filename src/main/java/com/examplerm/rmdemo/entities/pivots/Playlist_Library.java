package com.examplerm.rmdemo.entities.pivots;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.Playlist;

@Entity
@Table(name = "artist_library")
public class Playlist_Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Playlist artist;

    @ManyToOne
    private Library library;
}
