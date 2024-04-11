package com.openclassrooms.mddapi.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateCommentRequest {
    @NotBlank
    @Size(max = 2000)
    private String text;
}
