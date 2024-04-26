package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.mapper.SubjectMapper;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
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

    /**
     * Action to get all subjects
     * @return ResponseEntity<List<SubjectDto>>: all subjects
     */
    @GetMapping()
    public ResponseEntity<List<SubjectDto>> getAll() {
        List<Subject> subjects = this.subjectService.getAll();
        return ResponseEntity.ok().body(this.subjectMapper.toDto(subjects));
    }

    /**
     * Action to get all subjects followed by the user
     * @return ResponseEntity<List<SubjectDto>>: all subjects followed by the user
     */
    @GetMapping("/user")
    public ResponseEntity<List<SubjectDto>> getFollowedSubjects() {
        List<Subject> subjects = this.subjectService.getFollowedSubjects();
        return ResponseEntity.ok().body(this.subjectMapper.toDto(subjects));
    }

    /**
     * Action to make the user follow the subject
     * @param id: subject id
     * @return ResponseEntity<MessageResponse>: to indicate if the subscription is successful or not
     */
    @PostMapping("/{id}/follow")
    public ResponseEntity<MessageResponse> follow(@PathVariable("id") String id) {
        try {
            this.subjectService.follow(Long.parseLong(id));

            return ResponseEntity.ok(new MessageResponse("Subscription successful!"));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body((new MessageResponse("Subscription failed!")));
        }
    }

    /**
     * Action to make the user unfollow the subject
     * @param id: subject id
     * @return ResponseEntity<MessageResponse>: to indicate if the unsubscription is successful or not
     */
    @PostMapping("/{id}/unfollow")
    public ResponseEntity<MessageResponse> unfollow(@PathVariable("id") String id) {
        try {
            this.subjectService.unfollow(Long.parseLong(id));

            return ResponseEntity.ok(new MessageResponse("Subscription successful!"));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body((new MessageResponse("Subscription failed!")));
        }
    }
}
