package com.levent.demo.repository;

import com.levent.demo.models.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notifications, Integer> {
}
