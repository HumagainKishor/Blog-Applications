package com.example.repository;

import com.example.entity.Comment;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
@Repository

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
