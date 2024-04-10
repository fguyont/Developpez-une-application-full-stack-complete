package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.mapper.SubjectMapper;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
