package com.levent.demo.repository;

import com.levent.demo.models.CategoryEvent;
import com.levent.demo.models.CityEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEvent,Integer> {
//    private List<CategoryEvent> categoryEvents = new ArrayList<>();
//
//    public List<CategoryEvent> findAll(){
//        return categoryEvents;
//    }
//
//    public CategoryEvent findOne(Integer id){
//        return categoryEvents.stream()
//                .filter(cityEvent -> Objects.equals(cityEvent.getIdCategory(), id))
//                .findFirst().orElseThrow(() -> new RuntimeException("Category not found"));
//    }
//    public CategoryEvent findByName(String name){
//        return categoryEvents.stream()
//                .filter(cityEvent -> Objects.equals(cityEvent.getNameCategory(), name))
//                .findFirst().orElseThrow(() -> new RuntimeException("Category not found"));
//    }
//
//    @PostConstruct
//    private void init(){
//        categoryEvents.add(new CategoryEvent("Выставка"));
//        categoryEvents.add(new CategoryEvent("Спорт"));
//        categoryEvents.add(new CategoryEvent("Кино"));
//    }
}
