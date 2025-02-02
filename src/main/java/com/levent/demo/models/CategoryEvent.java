package com.levent.demo.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity

public class CategoryEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;
    private String nameCategory;
    @OneToMany(mappedBy = "categoryEvent", cascade = CascadeType.ALL)
    private Set<Events> events;

    public CategoryEvent(String nameCategory)
    {
        this.nameCategory = nameCategory;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Set<Events> getEvents() {
        return events;
    }

    public void setEvents(Set<Events> events) {
        this.events = events;
    }
}
