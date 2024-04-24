package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.payload.request.CreateCommentRequest;
import com.openclassrooms.mddapi.service.CommentService;
import com.openclassrooms.mddapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @Autowired
    CommentMapper commentMapper;

    // Currently not used in the application but can be useful for tests and debug
    @GetMapping("/api/comment")
    public ResponseEntity<?> getAll() {
        List<Comment> comments = this.commentService.getAll();
        return ResponseEntity.ok().body(this.commentMapper.toDto(comments));
    }

    @GetMapping("api/post/{postId}/comment")
    public ResponseEntity<?> getByPostId(@PathVariable("postId") String postId) {
        List<Comment> comments = this.commentService.getByPostId(Long.valueOf(postId));
        return ResponseEntity.ok().body(this.commentMapper.toDto(comments));
    }

    @PostMapping("api/post/{postId}/comment")
    public ResponseEntity<?> create(@PathVariable("postId") String postId, @Valid @RequestBody CreateCommentRequest createCommentRequest) {
        try {
            Comment comment = this.commentService.create(new Comment(createCommentRequest.getText(), postService.getById(Long.valueOf(postId))));

            return ResponseEntity.ok().body(this.commentMapper.toDto(comment));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
