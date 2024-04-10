package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.SubjectService;
import com.openclassrooms.mddapi.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {SubjectService.class, UserService.class}, imports = {Arrays.class, Collectors.class, Post.class, User.class, Subject.class, Collections.class, Optional.class})
public abstract class PostMapper implements EntityMapper<PostDto, Post> {

    @Autowired
    SubjectService subjectService;
    @Autowired
    UserService userService;

    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "text", target = "text"),
            @Mapping(source = "date", target = "date"),
            @Mapping(target = "subject", expression = "java(this.subjectService.getById(postDto.getSubjectId()))"),
            @Mapping(target = "user", expression = "java(this.userService.getById(postDto.getUserId()))"),
    })
    public abstract Post toEntity(PostDto postDto);


    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "text", target = "text"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "post.subject.id", target = "subjectId"),
            @Mapping(source = "post.subject.name", target = "subjectName"),
            @Mapping(source = "post.user.id", target = "userId"),
            @Mapping(source = "post.user.name", target = "userName"),
    })
    public abstract PostDto toDto(Post post);
}
