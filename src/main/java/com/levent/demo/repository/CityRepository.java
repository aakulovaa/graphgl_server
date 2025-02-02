package com.levent.demo.repository;

import com.levent.demo.models.CityEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface CityRepository extends JpaRepository<CityEvent, Integer> {
//    private List<CityEvent> cityEvents = new ArrayList<>();
//
//    public List<CityEvent> findAll(){
//        return cityEvents;
//    }
//
//    public CityEvent findOne(Integer id){
//        return cityEvents.stream()
//                .filter(cityEvent -> Objects.equals(cityEvent.getIdCity(), id))
//                .findFirst().orElseThrow(() -> new RuntimeException("City not found"));
//    }
//    public CityEvent findByName(String name){
//        return cityEvents.stream()
//                .filter(cityEvent -> Objects.equals(cityEvent.getNameCity(), name))
//                .findFirst().orElseThrow(() -> new RuntimeException("City not found"));
//    }
//
//    @PostConstruct
//    private void init(){
//        cityEvents.add(new CityEvent("Воронеж"));
//        cityEvents.add(new CityEvent("Москва"));
//        cityEvents.add(new CityEvent("Орел"));
//    }
}
