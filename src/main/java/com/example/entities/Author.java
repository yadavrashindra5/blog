package com.example.entities;

import com.example.validate.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "authorId")
    private List<Content>content;
}
