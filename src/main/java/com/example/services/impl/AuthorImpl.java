package com.example.services.impl;

import com.example.dtos.AuthorDto;
import com.example.entities.Author;
import com.example.exceptions.ResourceNotFoundException;
import com.example.repositories.AuthorRepository;
import com.example.services.AuthorService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorImpl implements AuthorService {
    Logger logger= LoggerFactory.getLogger(AuthorService.class);
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        String authorId= UUID.randomUUID().toString();
        authorDto.setAuthorId(authorId);
        Author author= mapper.map(authorDto, Author.class);
        Author savedAuthor= authorRepository.save(author);
        return mapper.map(savedAuthor, AuthorDto.class);
    }

    @Override
    public AuthorDto getSingleAuthor(String authorId) {
        Author author= authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return mapper.map(author,AuthorDto.class);
    }

    @Override
    public List<AuthorDto> getAllAuthor() {
        List<Author> allAuthor= authorRepository.findAll();
        List<AuthorDto>allAuthorDto=new ArrayList<>();
        List<AuthorDto> listOfAuthorDto= allAuthor.stream().map((author) -> mapper.map(author, AuthorDto.class)).toList();
        return listOfAuthorDto;
    }

    @Override
    public AuthorDto updateAuthor(AuthorDto authorDto,String authorId) {
        Author author=authorRepository.findById(authorId).orElseThrow(()->new ResourceNotFoundException("Give author not found"));
        logger.info("author is {}",author);
        AuthorDto authorDto1=mapper.map(author,AuthorDto.class);
        authorDto1.setAuthorName(authorDto.getAuthorName());
        authorDto1.setAuthorAddress(authorDto.getAuthorAddress());
        authorDto1.setAuthorGender(authorDto.getAuthorGender());
        author=mapper.map(authorDto1,Author.class);
        Author savedAuthor = authorRepository.save(author);
        return mapper.map(savedAuthor,AuthorDto.class);
//        Author authorData= mapper.map(authorDto, Author.class);
//        Author savedAuthor= authorRepository.save(authorData);
//        return mapper.map(savedAuthor,AuthorDto.class);
    }

    @Override
    public AuthorDto uploadImage(AuthorDto authorDto) {
        Author authorData= mapper.map(authorDto, Author.class);
        Author savedAuthor= authorRepository.save(authorData);
        return mapper.map(savedAuthor,AuthorDto.class);
    }

    @Override
    public AuthorDto deleteAuthor(String authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Give author id not found"));
        authorRepository.delete(author);
        return mapper.map(author,AuthorDto.class);
    }
}
