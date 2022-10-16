package com.examplerm.rmdemo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.pivots.User_Playlist;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")  
    private List<User_Playlist> user_Playlists;

    @OneToOne
    @JoinColumn(name="tokenFacebook_id", referencedColumnName = "id")
    private TokenFacebook tokenFacebook;

    @OneToOne
    @JoinColumn(name="library_id", referencedColumnName = "id")
    private Library library;
}
