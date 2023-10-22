package com.example.dtos;

import com.example.entities.Content;
import com.example.validate.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthorDto {
    private String authorId;
    @NotBlank(message = "Name is mandatory")
    private String authorName;
    @NotBlank(message = "Address is mandatory")
    private String authorAddress;
    @NotBlank(message = "Gender is mandatory")
    @Gender
    private String authorGender;
    private String authorImage;
    private List<Content>content;
}
