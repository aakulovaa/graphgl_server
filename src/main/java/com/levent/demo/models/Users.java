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

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private Set<Follow> following = new HashSet<>();
    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private Set<Follow> followers = new HashSet<>();

    @Column(columnDefinition = "integer default 0")
    private Integer followersCount;
    @Column(columnDefinition = "integer default 0")
    private Integer followingCount;


    @Column(columnDefinition = "integer default 0")
    private Integer countOfAttendedEvents;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Attended> eventsAttended = new HashSet<>();


    @Column(columnDefinition = "integer default 0")
    private Integer countOfPublishedEvents;

    @ManyToMany
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followee_id")
    )
    private Set<Users> follows;

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


    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "attended_events",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_event")
    )
    private Set<Events> attendedEvents;


    public Users() {
    }

    public Users(Integer idUser, String loginUser, String emailUser, String passwordUser, String accountType, String imageSrcUser, Set<Users> follows, Set<News> newsUser, Set<Chats> sentMessages, Set<Chats> receivedMessages, Set<Notifications> notifUser, Set<Events> attendedEvents) {
        this.idUser = idUser;
        this.loginUser = loginUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.accountType = accountType;
        this.imageSrcUser = imageSrcUser;
        this.follows = follows;
        this.newsUser = newsUser;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
        this.notifUser = notifUser;
        this.attendedEvents = attendedEvents;
    }

    public Users(Integer idUser, String loginUser, String emailUser, String passwordUser, String accountType, String imageSrcUser, Set<News> newsUser, Set<Chats> sentMessages, Set<Chats> receivedMessages, Set<Notifications> notifUser) {
        this.idUser = idUser;
        this.loginUser = loginUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.accountType = accountType;
        this.imageSrcUser = imageSrcUser;

        this.newsUser = newsUser;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
        this.notifUser = notifUser;
    }

    public Users(Integer idUser, String loginUser, String emailUser, String passwordUser, String accountType, String imageSrcUser, Set<News> newsUser, Set<Chats> sentMessages, Set<Chats> receivedMessages, Set<Notifications> notifUser, Set<Events> attendedEvents) {
        this.idUser = idUser;
        this.loginUser = loginUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.accountType = accountType;
        this.imageSrcUser = imageSrcUser;

        this.newsUser = newsUser;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
        this.notifUser = notifUser;
        this.attendedEvents = attendedEvents;
    }

    public Set<Users> getFollows() {
        return follows;
    }

    public void setFollows(Set<Users> follows) {
        this.follows = follows;
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

    public Set<Events> getAttendedEvents() {
        return attendedEvents;
    }

    public void setAttendedEvents(Set<Events> attendedEvents) {
        this.attendedEvents = attendedEvents;
    }

    public void addAttendedEvent(Events events){
        this.attendedEvents.add(events);
    }

    public Users(Integer idUser, String loginUser, String emailUser, String passwordUser, String accountType, String imageSrcUser, Integer followersCount, Integer followingCount, Integer countOfPublishedEvents, Set<Users> follows, Set<News> newsUser, Set<Chats> sentMessages, Set<Chats> receivedMessages, Set<Notifications> notifUser, Set<Events> attendedEvents) {
        this.idUser = idUser;
        this.loginUser = loginUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.accountType = accountType;
        this.imageSrcUser = imageSrcUser;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.countOfPublishedEvents = countOfPublishedEvents;
        this.follows = follows;
        this.newsUser = newsUser;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
        this.notifUser = notifUser;
        this.attendedEvents = attendedEvents;
    }

    public Users(Integer idUser, String loginUser, String emailUser, String passwordUser, String accountType, String imageSrcUser, Set<Follow> following, Set<Follow> followers, Integer followersCount, Integer followingCount, Integer countOfPublishedEvents, Set<Users> follows, Set<News> newsUser, Set<Chats> sentMessages, Set<Chats> receivedMessages, Set<Notifications> notifUser, Integer countOfAttendedEvents, Set<Events> attendedEvents) {
        this.idUser = idUser;
        this.loginUser = loginUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.accountType = accountType;
        this.imageSrcUser = imageSrcUser;
        this.following = following;
        this.followers = followers;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.countOfPublishedEvents = countOfPublishedEvents;
        this.follows = follows;
        this.newsUser = newsUser;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
        this.notifUser = notifUser;
        this.countOfAttendedEvents = countOfAttendedEvents;
        this.attendedEvents = attendedEvents;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    public Integer getCountOfPublishedEvents() {
        return countOfPublishedEvents;
    }

    public void setCountOfPublishedEvents(Integer countOfPublishedEvents) {
        this.countOfPublishedEvents = countOfPublishedEvents;
    }


    public Set<Follow> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Follow> following) {
        this.following = following;
    }

    public Set<Follow> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Follow> followers) {
        this.followers = followers;
    }

    public Integer getCountOfAttendedEvents() {
        return countOfAttendedEvents;
    }

    public void setCountOfAttendedEvents(Integer countOfAttendedEvents) {
        this.countOfAttendedEvents = countOfAttendedEvents;
    }

    public Users(Integer idUser, String loginUser, String emailUser, String passwordUser, String accountType, String imageSrcUser, Set<Follow> following, Set<Follow> followers, Integer followersCount, Integer followingCount, Integer countOfAttendedEvents, Set<Attended> eventsAttended, Integer countOfPublishedEvents, Set<Users> follows, Set<News> newsUser, Set<Chats> sentMessages, Set<Chats> receivedMessages, Set<Notifications> notifUser, Set<Events> attendedEvents) {
        this.idUser = idUser;
        this.loginUser = loginUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.accountType = accountType;
        this.imageSrcUser = imageSrcUser;
        this.following = following;
        this.followers = followers;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.countOfAttendedEvents = countOfAttendedEvents;
        this.eventsAttended = eventsAttended;
        this.countOfPublishedEvents = countOfPublishedEvents;
        this.follows = follows;
        this.newsUser = newsUser;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
        this.notifUser = notifUser;
        this.attendedEvents = attendedEvents;
    }

    public Set<Attended> getEventsAttended() {
        return eventsAttended;
    }

    public void setEventsAttended(Set<Attended> eventsAttended) {
        this.eventsAttended = eventsAttended;
    }
}
