package com.levent.demo.controllers;

import com.levent.demo.models.Events;
import com.levent.demo.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendedResult {
    private Users currentUser;
    private Events currentEvent;

    public AttendedResult(Users currentUser, Events currentEvent) {
        this.currentUser = currentUser;
        this.currentEvent = currentEvent;
    }
}
