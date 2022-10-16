package com.examplerm.rmdemo.repositories.pivotsRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.pivots.User_Playlist;

@Repository
public interface IUser_PlaylistRepository extends JpaRepository<User_Playlist, Long> {
    
}
