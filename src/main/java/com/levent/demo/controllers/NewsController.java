package com.levent.demo.controllers;

import com.levent.demo.models.News;
import com.levent.demo.models.Users;
import com.levent.demo.repository.NewsRepository;
import com.levent.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;
import java.util.Optional;

public class NewsController {
    @Autowired
    private final NewsRepository newsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @QueryMapping
    public List<News> allNews(){
        return newsRepository.findAll();
    }

    @QueryMapping
    public News oneNews(@Argument Integer idNews){
        Optional<News> newsOptional = newsRepository.findById(idNews);
        if (newsOptional.isEmpty()){
            throw new RuntimeException("News not found");
        }
        return newsOptional.get();
    }

    @MutationMapping
    public News createNews(@Argument Integer userNewsId,@Argument String titleNews,
                           @Argument String descNews, @Argument String imgSrcNews,@Argument String createdAt){
        Users userNews = usersRepository.findById(userNewsId).orElseThrow(()->new RuntimeException("User News not found"));
        News news = new News();
        news.setUserNews(userNews);
        news.setTitleNews(titleNews);
        news.setDescNews(descNews);
        news.setImgSrcNews(imgSrcNews);
        news.setCreatedAt(createdAt);
        newsRepository.save(news);
        return news;
    }

    @MutationMapping
    public News updateNews(@Argument Integer idNews, @Argument Integer userNewsId,@Argument String titleNews,
                           @Argument String descNews, @Argument String imgSrcNews,@Argument String createdAt){
        News news = newsRepository.findById(idNews).orElseThrow(()-> new RuntimeException("News not found"));
        if (userNewsId != null){
            Users userNews = usersRepository.findById(userNewsId).orElseThrow(()->new RuntimeException("User News not found"));
            news.setUserNews(userNews);
        }
        if(titleNews != null){
            news.setTitleNews(titleNews);
        }
        if(descNews != null){
            news.setDescNews(descNews);
        }
        if(imgSrcNews != null){
            news.setImgSrcNews(imgSrcNews);
        }
        if(createdAt!=null){
            news.setCreatedAt(createdAt);
        }

        return newsRepository.save(news);
    }

    @MutationMapping
    public Boolean deleteNews(@Argument Integer idNews){
        newsRepository.deleteById(idNews);
        return true;
    }
}
