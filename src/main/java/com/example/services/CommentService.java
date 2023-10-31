package com.example.services;

import com.example.dtos.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,String contentId);
    CommentDto getSingleComment(String commentId);
    List<CommentDto> getAllComment();
    CommentDto deleteComment(String commentId);
}
