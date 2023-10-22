package com.example.entities;

import com.example.validate.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Author {
    @Id
    private String authorId;
    private String authorName;
    private String authorAddress;
    private String authorGender;
    private String authorImage;
}
