package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.Playlist;

@Repository
public interface IPlaylistRepository extends JpaRepository<Playlist,Long> {
    
}