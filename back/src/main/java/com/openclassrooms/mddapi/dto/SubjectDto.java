package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

        private Long id;

        @NotBlank
        @Size(max = 50)
        private String name;

        @NotBlank
        @Size(max = 200)
        private String description;

        private List<Long> userIds;

}
