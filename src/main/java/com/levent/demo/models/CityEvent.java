package com.levent.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Entity
@Data
public class CityEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCity;
    private String nameCity;
    @OneToMany(mappedBy = "cityEvent", cascade = CascadeType.ALL)
    private Set<Events> events;

    public CityEvent(String nameCity) {
        this.nameCity = nameCity;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public Set<Events> getEvents() {
        return events;
    }

    public void setEvents(Set<Events> events) {
        this.events = events;
    }
}
