package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplerm.rmdemo.entities.Podcast;

public interface IPodcastRepository extends JpaRepository<Podcast, Long> {

}
