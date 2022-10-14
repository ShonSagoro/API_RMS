package com.examplerm.rmdemo.repositories.pivotsRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplerm.rmdemo.entities.pivots.Song_Playlist;

public interface ISong_PlaylistRepository extends JpaRepository<Song_Playlist, Long>{
    
}
