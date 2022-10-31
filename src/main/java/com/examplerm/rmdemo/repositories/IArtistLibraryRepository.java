package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.pivots.ArtistLibrary;

@Repository
public interface IArtistLibraryRepository extends JpaRepository<ArtistLibrary, Long>{


}
