package com.example.controller;

import com.example.payloads.CommentDto;
import com.example.service.CommentService;
import io.micronaut.http.annotation.*;

@Controller("api/comment")

public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @Post("/create/postId/{postId}")
    public CommentDto createComment(@Body CommentDto commentDto,@PathVariable Long postId){
        return this.commentService.createComment(commentDto,postId);
    }
    @Delete("/delete/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        this.commentService.deleteComment(commentId);
    }

}
