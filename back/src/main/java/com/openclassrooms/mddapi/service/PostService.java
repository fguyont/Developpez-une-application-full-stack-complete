package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post getById(Long id) {
        return this.postRepository.findById(id).orElse(null);
    }
}
