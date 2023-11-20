package com.example.repository;

import com.example.entity.Category;
import com.example.entity.Post;
import com.example.entity.Users;
import com.example.payloads.PostDto;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Post,Long> {
    List<Post> findByUsers(Users user);
    List<Post> findByCategory(Category category);
   // List<PostDto> findByTitleContaining(String keywords);
}
