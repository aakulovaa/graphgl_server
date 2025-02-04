package com.levent.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private String loginUser;
    private String emailUser;
    private String passwordUser;
    private String accountType;
    private String imageSrcUser;
    @Column(columnDefinition = "integer default 0")
    private Integer countOfSubscribers;
    @Column(columnDefinition = "integer default 0")
    private Integer countOfSubscription;
    @Column(columnDefinition = "integer default 0")
    private Integer countOfPublishedEvents;

    @OneToMany(mappedBy = "userNews", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<News> newsUser = new HashSet<>();

    @OneToMany(mappedBy = "userSender", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Chats> sentMessages;
    @OneToMany(mappedBy = "userReceiver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Chats> receivedMessages;

    @OneToMany(mappedBy = "userNotification", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Notifications> notifUser = new HashSet<>();


    public Users() {
    }

    public Users(Integer idUser, String loginUser, String emailUser, String passwordUser, String accountType, String imageSrcUser, Integer countOfSubscribers, Integer countOfSubscription, Integer countOfPublishedEvents, Set<News> newsUser, Set<Chats> sentMessages, Set<Chats> receivedMessages, Set<Notifications> notifUser) {
        this.idUser = idUser;
        this.loginUser = loginUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.accountType = accountType;
        this.imageSrcUser = imageSrcUser;
        this.countOfSubscribers = countOfSubscribers;
        this.countOfSubscription = countOfSubscription;
        this.countOfPublishedEvents = countOfPublishedEvents;
        this.newsUser = newsUser;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
        this.notifUser = notifUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getImageSrcUser() {
        return imageSrcUser;
    }

    public void setImageSrcUser(String imageSrcUser) {
        this.imageSrcUser = imageSrcUser;
    }

    public Integer getCountOfSubscribers() {
        return countOfSubscribers;
    }

    public void setCountOfSubscribers(Integer countOfSubscribers) {
        this.countOfSubscribers = countOfSubscribers;
    }

    public Integer getCountOfSubscription() {
        return countOfSubscription;
    }

    public void setCountOfSubscription(Integer countOfSubscription) {
        this.countOfSubscription = countOfSubscription;
    }

    public Integer getCountOfPublishedEvents() {
        return countOfPublishedEvents;
    }

    public void setCountOfPublishedEvents(Integer countOfPublishedEvents) {
        this.countOfPublishedEvents = countOfPublishedEvents;
    }

    public Set<News> getNewsUser() {
        return newsUser;
    }

    public void setNewsUser(Set<News> newsUser) {
        this.newsUser = newsUser;
    }

    public Set<Chats> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(Set<Chats> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public Set<Chats> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Set<Chats> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public Set<Notifications> getNotifUser() {
        return notifUser;
    }

    public void setNotifUser(Set<Notifications> notifUser) {
        this.notifUser = notifUser;
    }
}
