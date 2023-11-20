package com.example.repository;

import com.example.entity.Category;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

}
