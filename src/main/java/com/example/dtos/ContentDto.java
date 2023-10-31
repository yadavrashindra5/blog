package com.example.dtos;

import com.example.entities.Comment;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ContentDto {
    private String id;
    private String title;
    private String description;
    private Date publishedAt;
    private String image;
    private String authorId;
    private String authorName;
    private List<Comment>comment;
}
