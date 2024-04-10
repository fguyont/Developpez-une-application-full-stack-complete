package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.SubjectService;
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
public abstract class UserMapper implements EntityMapper<UserDto, User> {
    @Autowired
    SubjectService subjectService;

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
            @Mapping(target = "subjects", expression = "java(Optional.ofNullable(userDto.getSubjectIds()).orElseGet(Collections::emptyList).stream().map(subjectId -> { Subject subject = this.subjectService.getById(subjectId); if (subject != null) { return subject; } return null; }).collect(Collectors.toList()))"),
    })
    public abstract User toEntity(UserDto userDto);


    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
            @Mapping(target = "subjectIds", expression = "java(Optional.ofNullable(user.getSubjects()).orElseGet(Collections::emptyList).stream().map(u -> u.getId()).collect(Collectors.toList()))"),
    })
    public abstract UserDto toDto(User user);
}