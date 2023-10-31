package com.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Content {
    @Id
    private String id;
    private String title;
    @Column(columnDefinition = "longtext")
    private String description;
    private Date publishedAt;
    private String image;
    private String authorId;
    private String authorName;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "contentId")
    private List<Comment>comment;
}
