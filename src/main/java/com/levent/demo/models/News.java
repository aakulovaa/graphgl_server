package com.levent.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNews;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loginUser")
    @JsonBackReference
    private Users userNews;
    private String titleNews;
    private String descNews;
    private String imgSrcNews;
    private String createdAt;

    public News() {
    }

    public News(Integer idNews, Users userNews, String titleNews, String descNews, String imgSrcNews, String createdAt) {
        this.idNews = idNews;
        this.userNews = userNews;
        this.titleNews = titleNews;
        this.descNews = descNews;
        this.imgSrcNews = imgSrcNews;
        this.createdAt = createdAt;
    }

    public Integer getIdNews() {
        return idNews;
    }

    public void setIdNews(Integer idNews) {
        this.idNews = idNews;
    }

    public Users getUserNews() {
        return userNews;
    }

    public void setUserNews(Users userNews) {
        this.userNews = userNews;
    }

    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }

    public String getDescNews() {
        return descNews;
    }

    public void setDescNews(String descNews) {
        this.descNews = descNews;
    }

    public String getImgSrcNews() {
        return imgSrcNews;
    }

    public void setImgSrcNews(String imgSrcNews) {
        this.imgSrcNews = imgSrcNews;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
