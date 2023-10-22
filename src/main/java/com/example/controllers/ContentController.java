package com.example.controllers;

import com.example.dtos.AuthorDto;
import com.example.dtos.ContentDto;
import com.example.entities.Author;
import com.example.exceptions.ResourceNotFoundException;
import com.example.services.ContentService;
import com.example.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {
    Logger logger= LoggerFactory.getLogger(ContentController.class);
    @Autowired
    private ContentService contentService;
    @Autowired
    private FileService fileService;
    @Value("${content.image.path}")
    private  String imageUploadPath;
    @PostMapping
    public ResponseEntity<ContentDto> createContent(@RequestBody  ContentDto contentDto){
        logger.info("create content is {}",contentDto);
        ContentDto content = contentService.createContent(contentDto);
        return new ResponseEntity<>(content, HttpStatus.CREATED);
    }
    @GetMapping("/{contentId}")
    public ResponseEntity<ContentDto> getSingleContent(@PathVariable String contentId){
        ContentDto singleContent = contentService.getSingleContent(contentId);
        return new ResponseEntity<>(singleContent,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ContentDto>>getAllContent(){
        List<ContentDto> allContent = contentService.getAllContent();
        return new ResponseEntity<>(allContent,HttpStatus.OK);
    }
    @PutMapping("/{contentId}")
    public ResponseEntity<ContentDto>updateContent(@RequestBody  ContentDto contentDto,@PathVariable String contentId){
        ContentDto contentDto1 = contentService.updateContent(contentDto, contentId);
        return new ResponseEntity<>(contentDto1,HttpStatus.OK);
    }
    @PostMapping("/image/{contentId}")
    public ResponseEntity<ContentDto>uploadAuthorImage(@RequestParam(value = "image") MultipartFile authorImage, @PathVariable(value = "contentId")String contentId) throws IOException {
        String imageName= fileService.uploadFile(authorImage, imageUploadPath);
        ContentDto singleContent = contentService.getSingleContent(contentId);
        singleContent.setImage(imageName);
        ContentDto contentDto = contentService.uploadImge(singleContent);
        return new ResponseEntity<>(contentDto,HttpStatus.OK);
    }
    @GetMapping("/image/{contentId}")
    public void serviceUserImage(@PathVariable(value = "contentId")String contentId, HttpServletResponse response) throws IOException {
        ContentDto singleContent = contentService.getSingleContent(contentId);
        InputStream resource= fileService.getResource(imageUploadPath, singleContent.getImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
