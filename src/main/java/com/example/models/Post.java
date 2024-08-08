package com.example.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "category_id")
    private Long category_id;
    @Column(name = "posttheme")
    private String postTheme;
    @Column(name = "posttext")
    private String postText;
    @Column(name = "postdate")
    private Timestamp postDate;

    public Post() {
    }

    public Post(Long id, Long user_id, Long category_id, String postTheme, String postText, Timestamp postDate) {
        this.id = id;
        this.user_id = user_id;
        this.category_id = category_id;
        this.postTheme = postTheme;
        this.postText = postText;
        this.postDate = postDate;
    }

    public Post(Long user_id, Long category_id, String postTheme, String postText, Timestamp postDate) {
        this.user_id = user_id;
        this.category_id = category_id;
        this.postTheme = postTheme;
        this.postText = postText;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public String getPostTheme() {
        return postTheme;
    }

    public String getPostText() {
        return postText;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public void setPostTheme(String postTheme) {
        this.postTheme = postTheme;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

}