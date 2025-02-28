package com.levent.demo.repository;

import com.levent.demo.models.CategoryEvent;
import com.levent.demo.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Events, Integer> {
    // Найти товары по названию
    List<Events> findByNameEvent(String nameEvent);

    // Найти товары по категории
    List<Events> findByCategoryEvent(CategoryEvent categoryEvent);

    // Найти товары, которые находятся в одном сообществе (на основе рекомендаций)
    @Query("SELECT e FROM Events e WHERE e.id IN :idEvent")
    List<Events> findByCommunity(@Param("idEvent") Set<Integer> idEvent);

//    // Найти товары, добавленные в избранное пользователем
//    @Query("SELECT e FROM Events e JOIN e.favoritedBy u WHERE u.id = :idEvent")
//    List<Events> findSavedByUser(@Param("idEvent") Integer idEvent);

    // Найти товары, купленные пользователем
    @Query("SELECT e FROM Events e JOIN e.usersAttended u WHERE u.id = :idEvent")
    List<Events> findAttendedByUser(@Param("idEvent") Integer idEvent);

    // Найти товары с их категориями
    @Query("SELECT e FROM Events e LEFT JOIN FETCH e.categoryEvent")
    List<Events> findAllWithCategory();

}
