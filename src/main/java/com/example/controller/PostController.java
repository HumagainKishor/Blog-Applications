package com.example.controller;

import com.example.entity.Post;
import com.example.payloads.PostDto;
import com.example.payloads.PostResponse;
import com.example.repository.PostRepo;
import com.example.service.PostService;
import io.micronaut.http.annotation.*;

import java.util.List;

@Controller("api/posts")
public class PostController {
    private final PostRepo postRepo;
    private final PostService postService;

    public PostController(PostRepo postRepo, PostService postService) {
        this.postRepo = postRepo;
        this.postService = postService;
    }

    @io.micronaut.http.annotation.Post("/users/{userId}/category/{categoryId}/posts")
    public Post createPost(@Body PostDto postDto, @PathVariable Long userId,@PathVariable Long categoryId){
        return this.postService.createPost(postDto,userId,categoryId);
    }
    @Get("/getpostbyuser/{userId}")
    public List<PostDto> getPostByUser(@PathVariable Long userId){
        return postService.getPostByUser(userId);
    }
    @Get("/getpostbycategory/{categoryId}")
    public List<PostDto> getPostByCategory(@PathVariable Long categoryId){
        return postService.getPostByCategory(categoryId);
    }

    @Get("/getallpost")
    public List<PostDto> getAllPost(
            //@QueryValue(defaultValue = "5") Integer pageSize,
            //@QueryValue(defaultValue = "0") Integer pageNumber
            //@QueryValue(defaultValue = "false") boolean someFlag
    ){
        return this.postService.getAllPost();
    }
    @Get("/getpostbyid/{postId}")
    public PostDto getPostById(Long postId){
        return this.postService.getPostById(postId);
    }
    @Delete("/delete/{postId}")
    public void deletePost(@PathVariable Long postId){
        this.postService.deletePost(postId);
    }
    @Put("/update/{id}")
    public PostDto updatePost(@Body PostDto postDto,@PathVariable Long id){
        return this.postService.updatePost(postDto,id);
    }
    @Get("/search/{keywords}")
    public List<PostDto> searchPosts(@PathVariable String keywords){
        return this.postService.searchPosts(keywords);
    }
}
