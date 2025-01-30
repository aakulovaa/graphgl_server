package com.levent.demo.repository;

import com.levent.demo.models.CategoryEvent;
import com.levent.demo.models.CityEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CategoryRepository {
    private List<CategoryEvent> categoryEvents = new ArrayList<>();

    public List<CityEvent> findAll(){
        return categoryEvents;
    }

    public CategoryEvent findOne(Integer id){
        return categoryEvents.stream()
                .filter(cityEvent -> Objects.equals(cityEvent.idCategory(), id))
                .findFirst().orElseThrow(() -> new RuntimeException("Category not found"));
    }
    public CategoryEvent findByName(String name){
        return categoryEvents.stream()
                .filter(cityEvent -> Objects.equals(cityEvent.nameCategory(), name))
                .findFirst().orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @PostConstruct
    private void init(){
        categoryEvents.add(new CategoryEvent(1,"Выставка"));
        categoryEvents.add(new CategoryEvent(2,"Спорт"));
        categoryEvents.add(new CategoryEvent(3,"Кино"));
    }
}
