package com.examplerm.rmdemo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.pivots.SongPlaylist;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "songs")
@Getter @Setter
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String duration;
    
    @Column(nullable = false)
    private String creationDate;

    @Column(nullable = false)
    private String songUrl;
  
    @OneToMany(mappedBy = "song")  
    private List<SongPlaylist> song_Playlists;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Album album;
}
