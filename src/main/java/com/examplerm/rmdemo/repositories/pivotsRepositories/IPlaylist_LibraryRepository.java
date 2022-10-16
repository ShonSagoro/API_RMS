package com.examplerm.rmdemo.repositories.pivotsRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.pivots.Playlist_Library;

@Repository
public interface IPlaylist_LibraryRepository extends JpaRepository<Playlist_Library, Long>{
    
}
