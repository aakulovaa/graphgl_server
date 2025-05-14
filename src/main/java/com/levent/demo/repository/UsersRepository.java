package com.levent.demo.repository;

import com.levent.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    // Найти пользователей по логину
    List<Users> findByLoginUser(String loginUser);

    // Найти пользователей по email, password
    Users findByEmailUser(String emailUser);

    // Найти пользователей, которые находятся в одном сообществе
    @Query("SELECT u FROM Users u WHERE u.id IN :idUser")
    List<Users> findByCommunity(@Param("idUser") Set<Integer> idUser);

    // Найти пользователей, на которых подписан текущий пользователь
    @Query("SELECT u FROM Users u JOIN u.follows f WHERE f.id = :idUser")
    List<Users> findFollowers(@Param("idUser") Integer idUser);

    // Найти пользователей, которые подписаны на текущего пользователя
    @Query("SELECT u FROM Users u JOIN u.follows f WHERE f.id = :idUser")
    List<Users> findFollowees(@Param("idUser") Integer idUser);

    // Найти пользователей с их избранными мероприятиями
    @Query("SELECT u FROM Users u LEFT JOIN FETCH u.attendedEvents LEFT JOIN FETCH u.follows")
    List<Users> findAllWithInteractions();
}
