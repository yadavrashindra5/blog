package com.example.controllers;

import com.example.dtos.CommentDto;
import com.example.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/{contentId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable  String contentId){
        CommentDto creatingComment = commentService.createComment(commentDto, contentId);
        return new ResponseEntity<>(creatingComment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComment(){
        List<CommentDto> allComment = commentService.getAllComment();
        return new ResponseEntity<>(allComment,HttpStatus.OK);
    }
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto>getSingleComment(@PathVariable String commentId){
        CommentDto singleComment = commentService.getSingleComment(commentId);
        return new ResponseEntity<>(singleComment,HttpStatus.OK);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDto>deleteComment(@PathVariable String commentId){
        CommentDto commentDto = commentService.deleteComment(commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }
}
