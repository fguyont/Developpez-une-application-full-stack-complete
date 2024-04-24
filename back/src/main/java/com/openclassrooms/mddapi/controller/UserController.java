package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.request.SignupRequest;
import com.openclassrooms.mddapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            User user = this.userService.getById(Long.valueOf(id));

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.userMapper.toDto(user));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody SignupRequest signupRequest) {
        try {
            User user = this.userService.update(userService.getById(Long.parseLong(id)), signupRequest.getEmail(), signupRequest.getName(), signupRequest.getPassword());

            return ResponseEntity.ok().body(this.userMapper.toDto(user));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}