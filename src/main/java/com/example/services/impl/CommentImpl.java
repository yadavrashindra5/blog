package com.example.services.impl;

import com.example.dtos.CommentDto;
import com.example.entities.Comment;
import com.example.entities.Content;
import com.example.exceptions.ResourceNotFoundException;
import com.example.repositories.CommentRepository;
import com.example.repositories.ContentRepository;
import com.example.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ContentRepository contentRepository;
    @Override
    public CommentDto createComment(CommentDto commentDto, String contentId) {
        String commentId= UUID.randomUUID().toString();
        commentDto.setId(commentId);
        Comment comment = mapper.map(commentDto, Comment.class);

        Content content = contentRepository.findById(contentId).orElseThrow(() -> new ResourceNotFoundException("Content with the given id not found"));
        Comment savedComment = commentRepository.save(comment);
        content.getComment().add(savedComment);
        contentRepository.save(content);
        return mapper.map(comment,CommentDto.class);
    }

    @Override
    public CommentDto getSingleComment(String commentId) {
        Comment comment= commentRepository.findById(commentId).orElseThrow((() -> new ResourceNotFoundException("Given commentId not found")));
        return mapper.map(comment,CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> allComments= commentRepository.findAll();
        List<CommentDto> listOfComments= allComments.stream().map((comment -> mapper.map(comment, CommentDto.class))).toList();
        return listOfComments;
    }

    @Override
    public CommentDto deleteComment(String commentId) {
        Comment comment= commentRepository.findById(commentId).orElseThrow((() -> new ResourceNotFoundException("Given commentId not found")));
        commentRepository.delete(comment);
        return mapper.map(comment,CommentDto.class);
    }
}
