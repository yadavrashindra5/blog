package com.example.services;

import com.example.dtos.ContentDto;

import java.util.List;

public interface ContentService {
    ContentDto createContent(ContentDto contentDto,String authorId);
    ContentDto getSingleContent(String contentId);
    List<ContentDto>getAllContent();
    ContentDto updateContent(ContentDto contentDto,String contentId);
    ContentDto uploadImge(ContentDto contentDto);
    ContentDto deleteContent(String contentId);
}
