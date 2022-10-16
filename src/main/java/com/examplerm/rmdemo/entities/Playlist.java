package com.examplerm.rmdemo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.pivots.Playlist_Library;
import com.examplerm.rmdemo.entities.pivots.Song_Playlist;
import com.examplerm.rmdemo.entities.pivots.User_Playlist;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "playlists")
@Getter @Setter
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    
    @Column(nullable = false)
    private String dateCreation;
    
    @Column(nullable = false)
    private String duration;
    
    @OneToMany(mappedBy = "playlist")  
    private List<Playlist_Library> playlist_Libraries;
    
    
    @OneToMany(mappedBy = "playlist")  
    private List<Song_Playlist> song_Playlists;
    
    @OneToMany(mappedBy = "playlist")  
    private List<User_Playlist> user_Playlists;
}
