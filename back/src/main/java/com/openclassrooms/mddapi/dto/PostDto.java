package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String title;

    private LocalDateTime date;

    @NotBlank
    @Size(max = 5000)
    private String text;

    private Long userId;

    private Long subjectId;

}