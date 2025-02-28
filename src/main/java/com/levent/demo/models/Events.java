package com.levent.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Events {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvent;
    private String nameEvent;
    private String dateEvent;
    @ManyToOne @JoinColumn(name = "id_city", nullable = false)
            @JsonBackReference
    private CityEvent cityEvent;
    private String addressEvent;
    private String descriptionEvent;
    @Column(columnDefinition = "integer default 0")
    private Integer countOfPeople;
    @ManyToOne @JoinColumn(name = "id_category", nullable = false)
            @JsonBackReference
    private CategoryEvent categoryEvent;
    private String imageEvent;

    @ManyToMany(mappedBy = "attendedEvents", fetch = FetchType.EAGER)
    private Set<Users> usersAttended;

    public Events() {
    }

    public Events(Integer idEvent, String nameEvent, String dateEvent, CityEvent cityEvent, String addressEvent, String descriptionEvent, Integer countOfPeople, CategoryEvent categoryEvent, String imageEvent, Set<Users> usersAttended) {
        this.idEvent = idEvent;
        this.nameEvent = nameEvent;
        this.dateEvent = dateEvent;
        this.cityEvent = cityEvent;
        this.addressEvent = addressEvent;
        this.descriptionEvent = descriptionEvent;
        this.countOfPeople = countOfPeople;
        this.categoryEvent = categoryEvent;
        this.imageEvent = imageEvent;
        this.usersAttended = usersAttended;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public CityEvent getCityEvent() {
        return cityEvent;
    }

    public void setCityEvent(CityEvent cityEvent) {
        this.cityEvent = cityEvent;
    }

    public String getAddressEvent() {
        return addressEvent;
    }

    public void setAddressEvent(String addressEvent) {
        this.addressEvent = addressEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }

    public Integer getCountOfPeople() {
        return countOfPeople;
    }

    public void setCountOfPeople(Integer countOfPeople) {
        this.countOfPeople = countOfPeople;
    }

    public CategoryEvent getCategoryEvent() {
        return categoryEvent;
    }

    public void setCategoryEvent(CategoryEvent categoryEvent) {
        this.categoryEvent = categoryEvent;
    }

    public String getImageEvent() {
        return imageEvent;
    }

    public void setImageEvent(String imageEvent) {
        this.imageEvent = imageEvent;
    }

    public Set<Users> getUsersAttended() {
        return usersAttended;
    }

    public void setUsersAttended(Set<Users> usersAttended) {
        this.usersAttended = usersAttended;
    }
}
