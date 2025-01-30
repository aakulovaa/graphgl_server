package com.levent.demo.repository;

import com.levent.demo.models.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class EventRepository {
    private final CityRepository cityRepository;
    private final CategoryRepository categoryRepository;
    private List<Event> events = new ArrayList<>();

    public EventRepository(CityRepository cityRepository, CategoryRepository categoryRepository){
        this.cityRepository = cityRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Event> findAll(){
        return events;
    }

    public Event findOne(Integer id){
        return events.stream()
                .filter(event -> event.idEvent() == id)
                .findFirst().orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public Event findByName(String name){
        return events.stream()
                .filter(event -> Objects.equals(event.nameEvent(), name))
                .findFirst().orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @PostConstruct
    private void init(){
        events.add(new Event(1,"Фестиваль Музыки", "2025-02-25",
                cityRepository.findByName("Воронеж"),"Ленина 75",
                "Самые лучшие выступления!",0,
                categoryRepository.findOne(1), "le"));
        events.add(new Event(2,"Мамины пироги", "2025-02-25",
                cityRepository.findByName("Орел"),"Ленина 1",
                "Приходи, не пожалеешь",0,
                categoryRepository.findOne(3), "le"));
        events.add(new Event(3,"Гонки на выживание", "2025-02-25",
                cityRepository.findByName("Москва"),"Ленина 296",
                "Кто самый сильный? Кто самый быстрый? Все ждут только твоей заявки!",0,
                categoryRepository.findOne(2), "le"));
    }
}
