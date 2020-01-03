package com.ramon.catchup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.catchup.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
