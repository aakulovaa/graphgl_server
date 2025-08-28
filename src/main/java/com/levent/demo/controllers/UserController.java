package com.levent.demo.controllers;

import com.levent.demo.models.Attended;
import com.levent.demo.models.Events;
import com.levent.demo.models.Follow;
import com.levent.demo.models.Users;
import com.levent.demo.repository.AttendedRepository;
import com.levent.demo.repository.EventRepository;
import com.levent.demo.repository.FollowRepository;
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
    private final FollowRepository followRepository;
    private final AttendedRepository attendedRepository;
    private final EventRepository eventRepository;

    public UserController(UsersRepository usersRepository, FollowRepository followRepository, AttendedRepository attendedRepository, EventRepository eventRepository) {
        this.usersRepository = usersRepository;
        this.followRepository = followRepository;
        this.attendedRepository = attendedRepository;
        this.eventRepository = eventRepository;
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

    @QueryMapping
    public Users getUserByEmailAndPassword(@Argument String emailUser, @Argument String passwordUser){
        Users usersRepositoryByEmailUser = usersRepository.findByEmailUser(emailUser);
        if (usersRepositoryByEmailUser == null){
            throw new RuntimeException("User not found");
        }
        if (!usersRepositoryByEmailUser.getPasswordUser().equals(passwordUser)){
            throw new RuntimeException("Invalid password");
        }
        return usersRepositoryByEmailUser;
    }

    @QueryMapping
    public Boolean getFollowStatus(@Argument Integer idCurrentUser, @Argument Integer idUser){
        return followRepository.existsByFollowerIdUserAndFollowingIdUser(idCurrentUser, idUser);
    }


    @MutationMapping
    public Users createUser(@Argument String loginUser,
                            @Argument String emailUser, @Argument String passwordUser
                            ){
        Users users = new Users();
        users.setLoginUser(loginUser);
        users.setEmailUser(emailUser);
        users.setPasswordUser(passwordUser);
        usersRepository.save(users);
        return users;
    }

    @MutationMapping
    public Users updateUser(@Argument Integer idUser,
                            @Argument String loginUser,
                            @Argument String emailUser, @Argument String passwordUser,
                            @Argument String accountType,@Argument String imageSrcUser){
        Users users = usersRepository.findById(idUser).orElseThrow(()-> new RuntimeException("User not found"));
        if(loginUser!=null){
            users.setLoginUser(loginUser);
        }
        if(emailUser!=null) users.setEmailUser(emailUser);
        if(passwordUser!=null) users.setPasswordUser(passwordUser);
        if(accountType!=null) users.setAccountType(accountType);
        if(imageSrcUser!=null) users.setImageSrcUser(imageSrcUser);
        return usersRepository.save(users);
    }

    @MutationMapping
    public Users updatePasswordUser(@Argument Integer idUser,
                                 @Argument String passwordUser){
        Users users = usersRepository.findById(idUser).orElseThrow(()-> new RuntimeException("User not found"));
        if(passwordUser!=null){
            users.setPasswordUser(passwordUser);
        }
        return usersRepository.save(users);
    }

    @MutationMapping
    public Users updateLoginUser(@Argument Integer idUser,
                            @Argument String loginUser){
        Users users = usersRepository.findById(idUser).orElseThrow(()-> new RuntimeException("User not found"));
        if(loginUser!=null){
            users.setLoginUser(loginUser);
        }
        return usersRepository.save(users);
    }

    @MutationMapping
    public Users updateUserAccType(@Argument Integer idUser,
                                   @Argument String accountType){
        Users users = usersRepository.findById(idUser).orElseThrow(()-> new RuntimeException("User not found"));
        if(accountType!=null) users.setAccountType(accountType);
        return usersRepository.save(users);
    }

    @QueryMapping
    public List<Follow> allFollows(){
        return followRepository.findAll();
    }

    @QueryMapping
    public Follow oneFollow(@Argument Integer idCurrentUser, @Argument Integer idUser){
        Optional<Follow> followOptional = followRepository.selectByFollowerIdUserAndFollowingIdUser(idCurrentUser, idUser);
        if (followOptional.isEmpty()){
            throw new RuntimeException("Follow not found");
        }
        return followOptional.get();
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Integer idUser){
        usersRepository.deleteById(idUser);
        return true;
    }

    @MutationMapping
    public void deleteFollow(@Argument Integer idFollow){
        followRepository.deleteById(idFollow);
    }

    @MutationMapping
    public FollowResult followUser(@Argument Integer idCurrentUser, @Argument Integer idUser) {

        Users currentUser = usersRepository.findById(idCurrentUser)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Users userToFollow = usersRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (idCurrentUser.equals(idUser)) {
            throw new RuntimeException("Cannot follow yourself");
        }

        boolean isFollowing = followRepository.existsByFollowerIdUserAndFollowingIdUser(
                idCurrentUser, idUser);

        if (isFollowing) {
            deleteFollow(oneFollow(idCurrentUser, idUser).getIdFollow());
        } else {
            Follow follow = new Follow();
            follow.setFollower(currentUser);
            follow.setFollowing(userToFollow);
            followRepository.save(follow);
        }

        // Обновляем счетчики
        int newFollowersCount = followRepository.countFollowers(idUser);
        int newFollowingCount = followRepository.countFollowing(idCurrentUser);

        userToFollow.setFollowersCount(newFollowersCount);
        currentUser.setFollowingCount(newFollowingCount);

        usersRepository.save(userToFollow);
        usersRepository.save(currentUser);

        return new FollowResult(userToFollow, currentUser);
    }

    @QueryMapping
    public List<Attended> allAttended(){
        return attendedRepository.findAll();
    }

    @QueryMapping
    public Attended oneAttended(@Argument Integer idCurrentUser, @Argument Integer idCurrentEvent){
        Optional<Attended> attendOptional = attendedRepository.selectByUserIdUserAndEventIdEvent(idCurrentUser, idCurrentEvent);
        if (attendOptional.isEmpty()){
            throw new RuntimeException("Attend not found");
        }
        return attendOptional.get();
    }

    @QueryMapping
    public List<Attended> allAttendedByUser(@Argument Integer idCurrentUser){
        List<Attended> attendOptional = attendedRepository.selectByUserIdUser(idCurrentUser);
        if (attendOptional.isEmpty()){
            throw new RuntimeException("Attend not found");
        }
        return attendOptional;
    }

    @MutationMapping
    public void deleteAttended(@Argument Integer idAttended){
        attendedRepository.deleteById(idAttended);
    }

    @MutationMapping
    public AttendedResult attendedEvent(@Argument Integer idCurrentUser, @Argument Integer idCurrentEvent) {

        Users currentUser = usersRepository.findById(idCurrentUser)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Events currentEvent = eventRepository.findById(idCurrentEvent)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        boolean isAttending = attendedRepository.existsByUserIdUserAndEventIdEvent(
                idCurrentUser, idCurrentEvent);

        if (isAttending) {
            deleteAttended(oneAttended(idCurrentUser, idCurrentEvent).getIdAttended());
        } else {
            Attended attended = new Attended();
            attended.setUser(currentUser);
            attended.setEvent(currentEvent);
            attendedRepository.save(attended);
        }

        // Обновляем счетчики
        int newUsersCount = attendedRepository.countUsers(idCurrentUser);
        int newEventsCount = attendedRepository.countEvents(idCurrentEvent);

        currentUser.setCountOfAttendedEvents(newUsersCount);
        currentEvent.setCountOfPeople(newEventsCount);

        usersRepository.save(currentUser);
        eventRepository.save(currentEvent);

        return new AttendedResult(currentUser, currentEvent);
    }

}
