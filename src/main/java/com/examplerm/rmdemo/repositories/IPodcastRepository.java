package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.Podcast;

@Repository
public interface IPodcastRepository extends JpaRepository<Podcast, Long> {

}
