package com.example.controllers;

import com.example.dtos.AuthorDto;
import com.example.entities.Author;
import com.example.exceptions.ResourceNotFoundException;
import com.example.repositories.AuthorRepository;
import com.example.services.AuthorService;
import com.example.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    Logger logger= LoggerFactory.getLogger(AuthorController.class);
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired

    private FileService fileService;
    @Value("${author.profile.image.path}")
    private  String imageUploadPath;
    @PostMapping
    public ResponseEntity<AuthorDto>createAuthor(@Valid @RequestBody AuthorDto authorDto){
        AuthorDto savedAuthorDto= authorService.createAuthor(authorDto);
        ResponseEntity<AuthorDto> response=new ResponseEntity<>(savedAuthorDto, HttpStatus.CREATED);
        return response;
    }
    @GetMapping
    public ResponseEntity<List<AuthorDto>>getAllAuthor(){
        List<AuthorDto> allAuthor = authorService.getAllAuthor();
        return new ResponseEntity<>(allAuthor,HttpStatus.OK);
    }
    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDto>getSingleAuthor(@PathVariable String authorId){
        AuthorDto singleAuthor = authorService.getSingleAuthor(authorId);
        return new ResponseEntity<>(singleAuthor,HttpStatus.OK);
    }
    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorDto>updateAuthor(@Valid @RequestBody AuthorDto authorDto,@PathVariable String authorId){
        logger.info("authorDto is, {}{}",authorDto,authorId);
        AuthorDto authorDto1 = authorService.updateAuthor(authorDto, authorId);
        return new ResponseEntity<>(authorDto1,HttpStatus.OK);
    }
    @PostMapping("/image/{authorId}")
    public ResponseEntity<AuthorDto>uploadAuthorImage(@RequestParam(value = "authorImage")MultipartFile authorImage,@PathVariable(value = "authorId")String authorId) throws IOException {
        String imageName= fileService.uploadFile(authorImage, imageUploadPath);
        AuthorDto singleAuthor = authorService.getSingleAuthor(authorId);
        singleAuthor.setAuthorImage(imageName);
        AuthorDto authorDto = authorService.uploadImage(singleAuthor);
        return new ResponseEntity<>(authorDto,HttpStatus.OK);
    }
    @GetMapping("/image/{authorId}")
    public void serviceUserImage(@PathVariable(value = "authorId")String authorId, HttpServletResponse response) throws IOException {
        Author author= authorRepository.findById(authorId).orElseThrow(()->new ResourceNotFoundException("Give author with ${authorId} not found"));
        InputStream resource= fileService.getResource(imageUploadPath, author.getAuthorImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
