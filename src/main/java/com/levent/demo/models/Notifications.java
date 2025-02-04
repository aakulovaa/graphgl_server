package com.levent.demo.models;

import jakarta.persistence.*;

public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNotification;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private Users userNotification;
    private String nameNotification;
    private String imgSrcNotification;

    public Notifications() {
    }

    public Notifications(Integer idNotification, Users userNotification, String nameNotification, String imgSrcNotification) {
        this.idNotification = idNotification;
        this.userNotification = userNotification;
        this.nameNotification = nameNotification;
        this.imgSrcNotification = imgSrcNotification;
    }

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public Users getUserNotification() {
        return userNotification;
    }

    public void setUserNotification(Users userNotification) {
        this.userNotification = userNotification;
    }

    public String getNameNotification() {
        return nameNotification;
    }

    public void setNameNotification(String nameNotification) {
        this.nameNotification = nameNotification;
    }

    public String getImgSrcNotification() {
        return imgSrcNotification;
    }

    public void setImgSrcNotification(String imgSrcNotification) {
        this.imgSrcNotification = imgSrcNotification;
    }
}
