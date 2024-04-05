package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", uses = {UserService.class}, imports = {Arrays.class, Collectors.class, Subject.class, User.class, Collections.class, Optional.class})
public abstract class SubjectMapper implements EntityMapper<SubjectDto, Subject> {
    @Autowired
    UserService userService;

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(target = "users", expression = "java(Optional.ofNullable(subjectDto.getUserIds()).orElseGet(Collections::emptyList).stream().map(userId -> { User user = this.userService.findById(userId); if (user != null) { return user; } return null; }).collect(Collectors.toList()))"),
    })
    public abstract Subject toEntity(SubjectDto subjectDto);


    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(target = "userIds", expression = "java(Optional.ofNullable(subject.getUsers()).orElseGet(Collections::emptyList).stream().map(u -> u.getId()).collect(Collectors.toList()))"),
    })
    public abstract SubjectDto toDto(Subject subject);
}


