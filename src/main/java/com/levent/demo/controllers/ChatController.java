package com.levent.demo.controllers;

import com.levent.demo.models.Chats;
import com.levent.demo.models.Users;
import com.levent.demo.repository.ChatsRepository;
import com.levent.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ChatController {
    @Autowired
    private final ChatsRepository chatsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public ChatController(ChatsRepository chatsRepository) {
        this.chatsRepository = chatsRepository;
    }

    @QueryMapping
    public List<Chats> allChats(){
        return chatsRepository.findAll();
    }

    @QueryMapping
    public Chats oneChat(@Argument Integer idChat){
        Optional<Chats> chatsOptional = chatsRepository.findById(idChat);
        if (chatsOptional.isEmpty()){
            throw new RuntimeException("Chat not found");
        }
        return chatsOptional.get();
    }

    @MutationMapping
    public Chats createChat(@Argument Integer userSenderId,@Argument Integer userReceiverId,
                            @Argument String messageChat,@Argument String sendAt){
        Users userSender = usersRepository.findById(userSenderId).orElseThrow(()->new RuntimeException("User Sender not found"));
        Users userReceived = usersRepository.findById(userReceiverId).orElseThrow(()->new RuntimeException("User Received not found"));
        Chats chats = new Chats();
        chats.setUserSender(userSender);
        chats.setUserReceiver(userReceived);
        chats.setMessageChat(messageChat);
        chats.setSendAt(sendAt);
        chatsRepository.save(chats);
        return chats;
    }

    @MutationMapping
    public Chats updateChat(@Argument Integer idChat,@Argument Integer userSenderId,@Argument Integer userReceiverId,
                            @Argument String messageChat,@Argument String sendAt){
        Chats chats = chatsRepository.findById(idChat).orElseThrow(()-> new RuntimeException("Chat not found"));
       if (userSenderId != null){
           Users userSender = usersRepository.findById(userSenderId).orElseThrow(()->new RuntimeException("User Sender not found"));
            chats.setUserSender(userSender);
       }
       if(userReceiverId!=null){
           Users userReceived = usersRepository.findById(userReceiverId).orElseThrow(()->new RuntimeException("User Received not found"));
            chats.setUserReceiver(userReceived);
       }
       if(messageChat != null){
           chats.setMessageChat(messageChat);
       }
       if(sendAt != null){
           chats.setSendAt(sendAt);
       }

       return chatsRepository.save(chats);
    }

    @MutationMapping
    public Boolean deleteChat(@Argument Integer idChat){
        chatsRepository.deleteById(idChat);
        return true;
    }
}
