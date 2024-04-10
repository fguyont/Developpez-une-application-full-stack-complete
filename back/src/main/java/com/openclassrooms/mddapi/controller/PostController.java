package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    // Currently not used in the application but can be useful for tests and debug
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<Post> posts = this.postService.getAll();
        return ResponseEntity.ok().body(this.postMapper.toDto(posts));
    }

    @GetMapping()
    public ResponseEntity<?> getFeedPosts() {
        List<Post> posts = this.postService.getFeedPosts();
        return ResponseEntity.ok().body(this.postMapper.toDto(posts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            Post post = this.postService.getById(Long.valueOf(id));

            if (post == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.postMapper.toDto(post));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
