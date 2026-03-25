package com.example.controller;

import com.example.entity.Comment;
import com.example.repository.CommentRepository;
import com.example.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping
    public ApiResponse<List<Comment>> getAllComments() {
        return new ApiResponse<>(true, "Comments fetched", commentRepository.findAll());
    }

    @PostMapping
    public ApiResponse<Comment> createComment(@RequestBody Comment comment) {
        return new ApiResponse<>(true, "Comment created", commentRepository.save(comment));
    }
}