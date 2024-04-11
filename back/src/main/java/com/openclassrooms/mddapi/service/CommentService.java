package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public List<Comment> getByPostId(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        List<Comment> comments = new ArrayList<>();

        if (post.isPresent()) {
            comments = commentRepository.findByPost(post);
        }

        //TODO: To sort the comments by date descending

        return comments;
    }

    public Comment create(Comment comment) {
        Optional<User> user = this.userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user.isPresent()) {
            comment.setUser(user.get());
        }
        return this.commentRepository.save(comment);
    }
}
