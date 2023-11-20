package com.example.service;

import com.example.entity.Category;
import com.example.entity.Post;
import com.example.entity.Users;
import com.example.exceptions.ResourceNotFoundException;
import com.example.payloads.PostDto;
import com.example.payloads.PostResponse;
import com.example.repository.CategoryRepo;
import com.example.repository.PostRepo;
import com.example.repository.UserRepo;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class PostService {
    private final PostRepo postRepo;
    @Inject
    private ModelMapper modelMapper;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }
    @Inject
    private UserRepo userRepo;
    @Inject
    private CategoryRepo categoryRepo;


    //Create Post
    public Post createPost(PostDto postDto,Long userId, Long categoryId){
        Users user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));
        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUsers(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost,Post.class);

    }
    //Update Post
    @Transactional
    public PostDto updatePost(PostDto postDto,Long id){
        //Users user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","User Id",id));
        Post post = this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","post Id",id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(post.getImageName());
        Post updatePost = this.postRepo.save(post);
        return this.modelMapper.map(updatePost,PostDto.class);
    }
    //Delete Post
    public void deletePost(Long id){
        this.postRepo.deleteById(id);

    }
    //get all posts
    /*public List<PostDto> getAllPost(Integer pageSize,Integer pageNumber){
        Pageable p = Pageable.from(pageNumber,pageSize);
        System.out.println(p);
        Page<Post> postPage = this.postRepo.findAll(p);
        //List<Post> posts = this.postRepo.findAll();
        List<Post> allPosts = postPage.getContent();
        for (Post post : allPosts) {
            System.out.println("Post ID: " + post.getId());
            System.out.println("Title: " + post.getTitle());
            System.out.println("Content: " + post.getContent());
            // Print other attributes as needed
            System.out.println("---------------");
        }
        List<PostDto> postDtos = allPosts.stream().map(post-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }*/
    //implementing pagination
    public List<PostDto> getAllPost() {
        //Pageable p = Pageable.from(pageNumber,pageSize);
        //Page<Post> posts = this.postRepo.findAll(p);
        //List<Post> allPosts = posts.getContent();
        //List<PostDto> postDtos = allPosts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        //PostResponse postResponse = new PostResponse();
        //postResponse.setContent(postDtos);
        //postResponse.setPageNumber(posts.getPageNumber());
        //postResponse.setPageSize(posts.getSize());
        //postResponse.setTotalELements(posts.getNumberOfElements());
        //postResponse.setLastpage(posts.isEmpty());
        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDtos = posts.stream().map(post-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }


    //get post by id//implementing sorting
    public PostDto getPostById(Long id){
        Post post = this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",id));

        PostDto postDto = this.modelMapper.map(post,PostDto.class);
        return postDto;
    }
    //get post by category
    public List<PostDto> getPostByCategory(Long categoryId){
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category Id",categoryId));
        List<Post> posts= this.postRepo.findByCategory(cat);
        //
        /*for (Post post : posts) {
            System.out.println("Post ID: " + post.getId());
            System.out.println("Title: " + post.getTitle());
            System.out.println("Content: " + post.getContent());
            // Print other attributes as needed
            System.out.println("---------------");
        }*/
        //
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
    //get all post by user
    public List<PostDto> getPostByUser(Long userId){
        Users users = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user Id",userId));
        List<Post> posts = this.postRepo.findByUsers(users);
        List<PostDto> postDtos = posts.stream().map(post-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;


    }
    //search post
    public List<PostDto> searchPosts(String keywords){
        //List<PostDto> posts = this.postRepo.findByTitleContaining(keywords);
        //List<PostDto> postDtos = posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        //return postDtos;
        return null;
    }

}
