package com.levent.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "follows")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFollow;
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Users follower;
    @ManyToOne
    @JoinColumn(name = "following_id")
    private Users following;

    public Follow(Integer idFollow, Users follower, Users following) {
        this.idFollow = idFollow;
        this.follower = follower;
        this.following = following;
    }

    public Follow() {

    }

    public Integer getIdFollow() {
        return idFollow;
    }

    public void setIdFollow(Integer idFollow) {
        this.idFollow = idFollow;
    }

    public Users getFollower() {
        return follower;
    }

    public void setFollower(Users follower) {
        this.follower = follower;
    }

    public Users getFollowing() {
        return following;
    }

    public void setFollowing(Users following) {
        this.following = following;
    }
}
