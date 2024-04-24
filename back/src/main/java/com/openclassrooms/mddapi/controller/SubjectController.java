package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.mapper.SubjectMapper;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectMapper subjectMapper;


    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<Subject> subjects = this.subjectService.getAll();
        return ResponseEntity.ok().body(this.subjectMapper.toDto(subjects));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            Subject subject = this.subjectService.getById(Long.valueOf(id));

            if (subject == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.subjectMapper.toDto(subject));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/follow")
    public ResponseEntity<?> follow(@PathVariable("id") String id) {
        try {
            this.subjectService.follow(Long.parseLong(id));

            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/unfollow")
    public ResponseEntity<?> unfollow(@PathVariable("id") String id) {
        try {
            this.subjectService.unfollow(Long.parseLong(id));

            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
