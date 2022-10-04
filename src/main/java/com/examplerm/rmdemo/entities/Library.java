package com.examplerm.rmdemo.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.pivots.Album_Library;
import com.examplerm.rmdemo.entities.pivots.Artist_Library;
import com.examplerm.rmdemo.entities.pivots.Playlist_Library;
import com.examplerm.rmdemo.entities.pivots.Podcast_Library;

@Entity
@Table(name = "libraries")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "library")  
    private List<Album_Library> album_library;

    @OneToMany(mappedBy = "library")  
    private List<Artist_Library> artist_Libraries; 

    @OneToMany(mappedBy = "library")  
    private List<Podcast_Library> podcast_Libraries; 

    @OneToMany(mappedBy = "library")  
    private List<Playlist_Library> playlist_Libraries;
   
}
