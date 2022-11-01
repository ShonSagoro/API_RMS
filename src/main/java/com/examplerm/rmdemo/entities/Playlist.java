package com.examplerm.rmdemo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.pivots.PlaylistLibrary;
import com.examplerm.rmdemo.entities.pivots.SongPlaylist;
import com.examplerm.rmdemo.entities.pivots.UserPlaylist;

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
    private List<PlaylistLibrary> playlist_Libraries;
    
    @OneToMany(mappedBy = "playlist")  
    private List<SongPlaylist> song_Playlists;
    
    @OneToMany(mappedBy = "playlist")  
    private List<UserPlaylist> user_Playlists;

}
