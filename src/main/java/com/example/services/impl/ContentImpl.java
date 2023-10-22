package com.example.services.impl;

import com.example.dtos.ContentDto;
import com.example.entities.Content;
import com.example.exceptions.ResourceNotFoundException;
import com.example.repositories.ContentRepository;
import com.example.services.ContentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ContentImpl implements ContentService {
    Logger logger= LoggerFactory.getLogger(ContentService.class);
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ContentDto createContent(ContentDto contentDto) {
        String contentId= UUID.randomUUID().toString();
        Date date=new Date();
        contentDto.setPublishedAt(date);
        contentDto.setId(contentId);

        Content content= mapper.map(contentDto, Content.class);
        Content savedContent = contentRepository.save(content);
        return mapper.map(savedContent,ContentDto.class);
    }

    @Override
    public ContentDto getSingleContent(String contentId) {
        Content content = contentRepository.findById(contentId).orElseThrow(() -> new ResourceNotFoundException("Given content not found"));
        return mapper.map(content,ContentDto.class);
    }

    @Override
    public List<ContentDto> getAllContent() {
        List<Content> getAllContent = contentRepository.findAll();
        List<ContentDto> listOfContent= getAllContent.stream().map((content) -> mapper.map(content, ContentDto.class)).toList();
        return listOfContent;
    }

    @Override
    public ContentDto updateContent(ContentDto contentDto, String contentId) {
        ContentDto singleContent = getSingleContent(contentId);
        singleContent.setTitle(contentDto.getTitle());
        singleContent.setDescription(contentDto.getDescription());
        //convet contentDto to content entity
        Content content= mapper.map(singleContent, Content.class);
        Content savedContent = contentRepository.save(content);
        return mapper.map(savedContent,ContentDto.class);
    }

    @Override
    public ContentDto uploadImge(ContentDto contentDto) {
        Content content = mapper.map(contentDto, Content.class);
        Content savedContent = contentRepository.save(content);
        return mapper.map(savedContent,ContentDto.class);
    }
}
