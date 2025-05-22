package com.levent.demo.repository;

import com.levent.demo.models.Follow;
import com.levent.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository  extends JpaRepository<Follow, Integer> {
    boolean existsByFollowerIdUserAndFollowingIdUser(Integer followerId, Integer followingId);

    @Modifying
    @Query("DELETE FROM Follow f WHERE f.follower.idUser = :followerId AND f.following.idUser = :followingId")
    void deleteByFollowerIdUserAndFollowingIdUser(@Param("followerId") Integer followerId,
                                          @Param("followingId") Integer followingId);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.following.idUser = :idUser")
    int countFollowers(@Param("idUser") Integer idUser);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.follower.idUser = :idUser")
    int countFollowing(@Param("idUser") Integer idUser);
}
