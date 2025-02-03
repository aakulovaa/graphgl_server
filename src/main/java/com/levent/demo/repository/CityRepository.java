package com.levent.demo.repository;

import com.levent.demo.models.CityEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEvent, Integer> {
}
