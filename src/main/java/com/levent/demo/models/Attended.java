package com.levent.demo.models;

import jakarta.persistence.*;

@Entity
public class Attended {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAttended;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Events event;

    public Attended(Integer idAttended, Users user, Events event) {
        this.idAttended = idAttended;
        this.user = user;
        this.event = event;
    }
    public Attended() {
    }

    public Integer getIdAttended() {
        return idAttended;
    }

    public void setIdAttended(Integer idAttended) {
        this.idAttended = idAttended;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }
}
