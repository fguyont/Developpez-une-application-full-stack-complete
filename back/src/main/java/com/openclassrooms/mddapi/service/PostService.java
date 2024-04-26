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

    /**
     * Service to get a post by id
     * @param id: post id
     * @return Post: the post
     */
    public Post getById(Long id) {
        return this.postRepository.findById(id).orElse(null);
    }

    /**
     * Service to get posts by subject
     * @param subject: the subject
     * @return List<Post>: all the posts by subject
     */
    private List<Post> getBySubject (Subject subject) {
        return this.postRepository.findBySubject(subject);
    }

    /**
     * Service to get feed posts
     * @return List<Post>: all the feed posts
     */
    public List<Post> getFeedPosts() {
        Optional<User> user = this.userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Post> posts = new ArrayList<>();

        if (user.isPresent()) {
            for (Subject subject : user.get().getSubjects()) {
                posts.addAll(getBySubject(subject));
            }
        }

        return posts;
    }

    /**
     * Service to get create a post
     * @param post: the post to create
     * @return post: the post created
     */
    public Post create (Post post) {
        Optional<User> user = this.userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user.isPresent()) {
            post.setUser(user.get());
        }
        return this.postRepository.save(post);
    }
}
