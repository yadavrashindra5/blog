package com.example.dtos;

import lombok.*;

import java.util.Date;

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
}
