package com.example.dtos;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentDto {
    private String id;
    private String comment;
}
