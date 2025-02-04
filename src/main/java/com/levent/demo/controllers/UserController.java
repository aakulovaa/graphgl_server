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
public class UserController {

    @Autowired
    private final UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @QueryMapping
    public List<Users> allUsers(){
        return usersRepository.findAll();
    }

    @QueryMapping
    public Users oneUser(@Argument Integer idUser){
        Optional<Users> usersOptional = usersRepository.findById(idUser);
        if (usersOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return usersOptional.get();
    }

    @MutationMapping
    public Users createUser(@Argument String loginUser,
                            @Argument String emailUser, @Argument String passwordUser,
                            @Argument String accountType,@Argument String imageSrcUser,
                            @Argument Integer countOfSubscribers, @Argument Integer countOfSubscription,
                            @Argument Integer countOfPublishedEvents){
        Users users = new Users();
        users.setLoginUser(loginUser);
        users.setEmailUser(emailUser);
        users.setPasswordUser(passwordUser);
        users.setAccountType(accountType);
        users.setImageSrcUser(imageSrcUser);
        users.setCountOfSubscribers(countOfSubscribers);
        users.setCountOfSubscription(countOfSubscription);
        users.setCountOfPublishedEvents(countOfPublishedEvents);
        usersRepository.save(users);
        return users;
    }

    @MutationMapping
    public Users updateUser(@Argument Integer idUser,
                            @Argument String loginUser,
                            @Argument String emailUser, @Argument String passwordUser,
                            @Argument String accountType,@Argument String imageSrcUser,
                            @Argument Integer countOfSubscribers, @Argument Integer countOfSubscription,
                            @Argument Integer countOfPublishedEvents){
        Users users = usersRepository.findById(idUser).orElseThrow(()-> new RuntimeException("User not found"));
        if(loginUser!=null){
            users.setLoginUser(loginUser);
        }
        if(emailUser!=null) users.setEmailUser(emailUser);
        if(passwordUser!=null) users.setPasswordUser(passwordUser);
        if(accountType!=null) users.setAccountType(accountType);
        if(imageSrcUser!=null) users.setImageSrcUser(imageSrcUser);
        if(countOfSubscribers!=null) users.setCountOfSubscribers(countOfSubscribers);
        if(countOfSubscription!=null) users.setCountOfSubscription(countOfSubscription);
        if(countOfPublishedEvents!=null) users.setCountOfPublishedEvents(countOfPublishedEvents);
        return usersRepository.save(users);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Integer idUser){
        usersRepository.deleteById(idUser);
        return true;
    }
}
