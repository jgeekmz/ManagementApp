package com.jgeekmz.ManagementApp.repositories;

import java.util.List;

import com.jgeekmz.ManagementApp.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

    List<Post> findAll();
}
