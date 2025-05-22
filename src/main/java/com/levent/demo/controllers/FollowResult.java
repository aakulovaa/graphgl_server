package com.levent.demo.controllers;

import com.levent.demo.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowResult {
    private Users user;
    private Users currentUser;

    public FollowResult(Users user, Users currentUser) {
        this.user = user;
        this.currentUser = currentUser;
    }
}