package com.levent.demo.controllers;

import com.levent.demo.models.CityEvent;
import com.levent.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CityEventController {
    @Autowired
    private final CityRepository cityRepository;

    public CityEventController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    @QueryMapping
    public List<CityEvent> allCities(){
        return cityRepository.findAll();
    }
    @QueryMapping
    public CityEvent oneCity(@Argument Integer idCity){
        Optional<CityEvent> cityEventOptional = cityRepository.findById(idCity);
        if (cityEventOptional.isEmpty()){
            throw new RuntimeException("City not found");
        }
        return cityEventOptional.get();
    }

    @MutationMapping
    public CityEvent createCity(@Argument String nameCity){
        CityEvent cityEvent = new CityEvent();
        cityEvent.setNameCity(nameCity);
        cityRepository.save(cityEvent);
        return cityEvent;
    }

    @MutationMapping
    public CityEvent updateCity(@Argument Integer idCity,@Argument String nameCity){
       CityEvent cityEvent = cityRepository.findById(idCity).orElseThrow(()->new RuntimeException("City not found"));
       if (nameCity != null){
           cityEvent.setNameCity(nameCity);
       }

       return cityRepository.save(cityEvent);
    }

    @MutationMapping
    public Boolean deleteCity(@Argument Integer idCity){
        cityRepository.deleteById(idCity);
        return true;
    }
}
