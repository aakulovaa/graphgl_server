package com.levent.demo.controllers;

import com.levent.demo.models.CategoryEvent;
import com.levent.demo.models.CityEvent;
import com.levent.demo.models.Events;
import com.levent.demo.repository.CategoryRepository;
import com.levent.demo.repository.CityRepository;
import com.levent.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EventController {

    @Autowired
    private final EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @QueryMapping
    public List<Events> allEvents(){
        return eventRepository.findAll();
    }

    public Events createEvent(Integer idEvent,
                              String nameEvent, String dateEvent,
                              Integer cityId,
                              String addressEvent,
                              String descriptionEvent, Integer countOfPeople,
                              Integer categoryId,
                              String imageEvent){
        CityEvent cityEvent = cityRepository.findById(cityId).orElseThrow(()->new RuntimeException("City not found"));
        CategoryEvent categoryEvent = categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category not found"));

        Events events = new Events();
        events.setNameEvent(nameEvent);
        events.setDateEvent(dateEvent);
        events.setCityEvent(cityEvent);
        events.setAddressEvent(addressEvent);
        events.setDescriptionEvent(descriptionEvent);
        events.setCountOfPeople(countOfPeople);
        events.setCategoryEvent(categoryEvent);
        events.setImageEvent(imageEvent);

        return eventRepository.save(events);
    }

    public Events updateEvent(Integer idEvent,
                              String nameEvent, String dateEvent,
                              Integer cityId,
                              String addressEvent,
                              String descriptionEvent, Integer countOfPeople,
                              Integer categoryId,
                              String imageEvent){
        Events events = eventRepository.findById(idEvent).orElseThrow(()->new RuntimeException("Event not found"));
        if(nameEvent != null) events.setNameEvent(nameEvent);
        if(dateEvent!=null) events.setDateEvent(dateEvent);
        if (cityId!=null) {
            CityEvent cityEvent = cityRepository.findById(cityId).orElseThrow(()->new RuntimeException("City not found"));
            events.setCityEvent(cityEvent);
        }
        if(addressEvent!=null) events.setAddressEvent(addressEvent);
        if(descriptionEvent!=null) events.setDescriptionEvent(descriptionEvent);
        if(countOfPeople!=null) events.setCountOfPeople(countOfPeople);
        if(categoryId!=null){
            CategoryEvent categoryEvent = categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category not found"));
            events.setCategoryEvent(categoryEvent);
        }
        if(imageEvent != null) events.setImageEvent(imageEvent);

        return eventRepository.save(events);
    }

    public Boolean deleteEvent(Integer idEvent){
        eventRepository.deleteById(idEvent);
        return true;
    }
}
