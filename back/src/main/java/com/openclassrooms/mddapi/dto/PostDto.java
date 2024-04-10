package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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

    //TODO: I might need more information about the user in the post like his name. I need an another solution (if I have, PostMapper to fix).
    private Long userId;

    private Long subjectId;

}