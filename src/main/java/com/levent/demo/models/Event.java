package com.levent.demo.models;

public record Event(Integer idEvent, String nameEvent, String dateEvent, CityEvent cityEvent, String addressEvent, String descriptionEvent, Integer countOfPeople, CategoryEvent categoryEvent, String imageEvent) {
}
