package com.examplerm.rmdemo.repositories.pivotsRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplerm.rmdemo.entities.pivots.User_Playlist;

public interface IUser_PlaylistRepository extends JpaRepository<User_Playlist, Long> {
    
}
