package com.levent.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Chats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idChat;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sentMessages")
    @JsonBackReference
    private Users userSender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receivedMessages")
    @JsonBackReference
    private Users userReceiver;
    private String messageChat;
    private String sendAt;

    public Chats() {
    }

    public Chats(Integer idChat, Users userSender, Users userReceiver, String messageChat, String sendAt) {
        this.idChat = idChat;
        this.userSender = userSender;
        this.userReceiver = userReceiver;
        this.messageChat = messageChat;
        this.sendAt = sendAt;
    }

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    public Users getUserSender() {
        return userSender;
    }

    public void setUserSender(Users userSender) {
        this.userSender = userSender;
    }

    public Users getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(Users userReceiver) {
        this.userReceiver = userReceiver;
    }

    public String getMessageChat() {
        return messageChat;
    }

    public void setMessageChat(String messageChat) {
        this.messageChat = messageChat;
    }

    public String getSendAt() {
        return sendAt;
    }

    public void setSendAt(String sendAt) {
        this.sendAt = sendAt;
    }
}
