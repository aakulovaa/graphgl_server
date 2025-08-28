package com.levent.demo.repository;

import com.levent.demo.models.Attended;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AttendedRepository  extends JpaRepository<Attended, Integer> {
    boolean existsByUserIdUserAndEventIdEvent(Integer userId, Integer eventId);

    @Query("SELECT a FROM Attended a WHERE a.user.idUser = :userId AND a.event.idEvent = :eventId")
    Optional<Attended> selectByUserIdUserAndEventIdEvent(@Param("userId") Integer userId,
                                                         @Param("eventId") Integer eventId);

    @Query("SELECT a FROM Attended a WHERE a.user.idUser = :userId")
    List<Attended> selectByUserIdUser(@Param("userId") Integer userId);

    @Query("SELECT COUNT(a) FROM Attended a WHERE a.user.idUser = :idUser")
    int countUsers(@Param("idUser") Integer idUser);

    @Query("SELECT COUNT(a) FROM Attended a WHERE a.event.idEvent = :idEvent")
    int countEvents(@Param("idEvent") Integer idEvent);
}
