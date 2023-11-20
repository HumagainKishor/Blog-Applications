package com.example.entity;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Serdeable
@Entity
@Table(name = "users")

public class Users {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    private String about;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
