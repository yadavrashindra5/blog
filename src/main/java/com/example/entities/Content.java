package com.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

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
}
