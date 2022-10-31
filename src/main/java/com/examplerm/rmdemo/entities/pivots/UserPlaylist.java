package com.examplerm.rmdemo.entities.pivots;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.Playlist;
import com.examplerm.rmdemo.entities.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_playlist")
@Getter @Setter
public class UserPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    private User user;

    @ManyToOne
    private Playlist playlist;
}
