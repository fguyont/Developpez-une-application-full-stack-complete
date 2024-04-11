package com.openclassrooms.mddapi.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreatePostRequest {
    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    @Size(max = 5000)
    private String text;

    private Long subjectId;

}
