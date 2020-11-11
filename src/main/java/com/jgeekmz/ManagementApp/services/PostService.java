package com.jgeekmz.ManagementApp.services;

import java.util.List;
import java.util.Optional;

import com.jgeekmz.ManagementApp.models.Post;
import com.jgeekmz.ManagementApp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    public PostRepository postRepository;

    // Get All Clients
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // Get Client By Id
    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    // Delete Client
    public void delete(int id) {
        postRepository.deleteById(id);
    }

    // Update Client
    public void save(Post post) {
        postRepository.save(post);
    }
}
