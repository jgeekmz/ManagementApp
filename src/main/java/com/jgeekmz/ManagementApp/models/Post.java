package com.jgeekmz.ManagementApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.sun.istack.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

@DynamicUpdate
@Entity
@Table(name = "post")
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NotNull
    private int id;

    @NotNull
    private String title;

    @NotNull
    private String category;

    @NotNull
    private String content;

    @NotNull
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd")
    private String postDate;

    public Post() {
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", category=" + category + ", content=" + content + ", postDate="
                + postDate + "]";
    }

    //Getters % Setters

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(@NotNull String postDate) {
        this.postDate = postDate;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
