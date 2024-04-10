package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubjectRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public Subject getById(Long id) {
        return this.subjectRepository.findById(id).orElse(null);
    }

    public void follow(Long id) {
        Subject subject = this.subjectRepository.findById(id).orElse(null);
        Optional<User> user = this.userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (subject == null || user.isEmpty()) {
            throw new NotFoundException();
        }

        boolean isFollowing;
        List<Long> userIds = new ArrayList<>();
        for (User u : subject.getUsers()) {
            userIds.add(u.getId());
        }

        isFollowing = userIds.contains(user.get().getId());

        if(isFollowing) {
            throw new BadRequestException();
        }

        subject.getUsers().add(user.get());

        this.subjectRepository.save(subject);
    }

    public void unfollow(Long id) {
        Subject subject = this.subjectRepository.findById(id).orElse(null);
        Optional<User> user = this.userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (subject == null || user.isEmpty()) {
            throw new NotFoundException();
        }

        boolean isFollowing;
        List<Long> userIds = new ArrayList<>();
        for (User u : subject.getUsers()) {
            userIds.add(u.getId());
        }

        isFollowing = userIds.contains(user.get().getId());

        if(!isFollowing) {
            throw new BadRequestException();
        }

        List<User> updatedUsers = new ArrayList<>();

        for (User u: subject.getUsers()) {
            if (!Objects.equals(u.getId(), user.get().getId())) {
                updatedUsers.add(u);
            }
        }

        subject.setUsers(updatedUsers);

        this.subjectRepository.save(subject);
    }
}
