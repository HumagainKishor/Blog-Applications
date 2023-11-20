package com.example.service;

import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.exceptions.ResourceNotFoundException;
import com.example.payloads.CommentDto;
import com.example.repository.CommentRepo;
import com.example.repository.PostRepo;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

@Singleton

public class CommentService {
    private final CommentRepo commentRepo;

    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }
    @Inject
    private ModelMapper modelMapper;
    @Inject
    private PostRepo postRepo;

    public CommentDto createComment(CommentDto commentDto,Long postId){
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        Comment com = this.modelMapper.map(commentDto,Comment.class);
        com.setPost(post);
        Comment savedCom = this.commentRepo.save(com);
        return this.modelMapper.map(savedCom,CommentDto.class);
    }
    public void deleteComment(Long commentId){
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment id",commentId));
        this.commentRepo.delete(comment);
    }
}
