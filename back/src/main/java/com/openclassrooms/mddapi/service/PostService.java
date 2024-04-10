package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post getById(Long id) {
        return this.postRepository.findById(id).orElse(null);
    }

    public List<Post> getBySubject (Subject subject) {
        return this.postRepository.findBySubject(subject);
    }

    public List<Post> getFeedPosts() {
        Optional<User> user = this.userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Post> posts = new ArrayList<>();

        if (user.isPresent()) {
            for (Subject subject : user.get().getSubjects()) {
                posts.addAll(getBySubject(subject));
            }
        }

        // TODO: It is possible to sort the posts by date descending
        return posts;
    }
}
