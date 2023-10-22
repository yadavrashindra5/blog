package com.example.services;

import com.example.dtos.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto createAuthor(AuthorDto authorDto);
    AuthorDto getSingleAuthor(String authorId);
    List<AuthorDto>getAllAuthor();
    AuthorDto updateAuthor(AuthorDto authorDto,String authorId);
    AuthorDto uploadImage(AuthorDto authorDto);
}
