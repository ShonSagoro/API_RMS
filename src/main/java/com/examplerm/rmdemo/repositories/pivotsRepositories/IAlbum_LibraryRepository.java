package com.examplerm.rmdemo.repositories.pivotsRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.pivots.Album_Library;

@Repository
public interface IAlbum_LibraryRepository extends JpaRepository<Album_Library, Long> {

}
