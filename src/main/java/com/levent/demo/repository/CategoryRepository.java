package com.levent.demo.repository;

import com.levent.demo.models.CategoryEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEvent,Integer> {
}
