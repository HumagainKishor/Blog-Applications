package com.example.payloads;

import com.example.entity.Category;
import com.example.entity.Comment;
import com.example.entity.Users;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Serdeable
@Introspected
public class PostDto {

    private Long id;
    private String title;
    private String content;
    //private String imageName="defaut.png";
    private String imageName;
    private Date addeDate;

    private  Set<CommentDto> comments = new HashSet<>();

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getAddeDate() {
        return addeDate;
    }

    public void setAddeDate(Date addeDate) {
        this.addeDate = addeDate;
    }
    @ManyToOne
    private Category category;
    @ManyToOne
    private Users users;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
