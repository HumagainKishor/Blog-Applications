package com.example.repository;

import com.example.entity.Users;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
}
