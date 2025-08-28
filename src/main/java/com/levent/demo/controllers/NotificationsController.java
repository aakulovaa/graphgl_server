package com.levent.demo.controllers;

import com.levent.demo.models.Notifications;
import com.levent.demo.models.Users;
import com.levent.demo.repository.NotificationsRepository;
import com.levent.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
@Controller
public class NotificationsController {
    @Autowired
    private final NotificationsRepository notificationsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public NotificationsController(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    @QueryMapping
    public List<Notifications> allNotifications(){
        return notificationsRepository.findAll();
    }

    @QueryMapping
    public Notifications oneNotification(@Argument Integer idNotification){
        Optional<Notifications> notificationsOptional = notificationsRepository.findById(idNotification);
        if (notificationsOptional.isEmpty()){
            throw new RuntimeException("Notification not found");
        }
        return notificationsOptional.get();
    }

    @MutationMapping
    public Notifications createNotification(@Argument Integer userNotificationId,
                                            @Argument String nameNotification,
                                            @Argument String imgSrcNotification){
        Users userNotification = usersRepository.findById(userNotificationId).orElseThrow(()->new RuntimeException("User Notification not found"));
        Notifications notifications = new Notifications();
        notifications.setUserNotification(userNotification);
        notifications.setNameNotification(nameNotification);
        notifications.setImgSrcNotification(imgSrcNotification);
        notificationsRepository.save(notifications);
        return notifications;
    }

    @MutationMapping
    public Notifications updateNotification(@Argument Integer idNotification,
                                            @Argument Integer userNotificationId,
                                            @Argument String nameNotification,
                                            @Argument String imgSrcNotification){
        Notifications notification = notificationsRepository.findById(idNotification).orElseThrow(()-> new RuntimeException("Notification not found"));
        if (userNotificationId != null){
            Users userNotification = usersRepository.findById(userNotificationId).orElseThrow(()->new RuntimeException("User Notification not found"));
            notification.setUserNotification(userNotification);
        }
        if(nameNotification != null){
            notification.setNameNotification(nameNotification);
        }
        if(imgSrcNotification != null){
            notification.setImgSrcNotification(imgSrcNotification);
        }
        return notificationsRepository.save(notification);
    }

    @MutationMapping
    public Boolean deleteNotification(@Argument Integer idNotification){
        notificationsRepository.deleteById(idNotification);
        return true;
    }
}
