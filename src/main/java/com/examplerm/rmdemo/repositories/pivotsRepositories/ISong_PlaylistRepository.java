package com.examplerm.rmdemo.repositories.pivotsRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.pivots.Song_Playlist;

@Repository
public interface ISong_PlaylistRepository extends JpaRepository<Song_Playlist, Long>{
    
}
