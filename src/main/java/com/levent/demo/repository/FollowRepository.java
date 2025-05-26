package com.levent.demo.repository;

import com.levent.demo.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FollowRepository  extends JpaRepository<Follow, Integer> {
    boolean existsByFollowerIdUserAndFollowingIdUser(Integer followerId, Integer followingId);

    @Query("SELECT f FROM Follow f WHERE f.follower.idUser = :followerId AND f.following.idUser = :followingId")
    Optional<Follow> selectByFollowerIdUserAndFollowingIdUser(@Param("followerId") Integer followerId,
                                                  @Param("followingId") Integer followingId);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.following.idUser = :idUser")
    int countFollowers(@Param("idUser") Integer idUser);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.follower.idUser = :idUser")
    int countFollowing(@Param("idUser") Integer idUser);
}
