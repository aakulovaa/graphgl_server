package com.levent.demo.graph;

import com.levent.demo.models.Users;
import com.levent.demo.repository.UsersRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserRecommender {
    private final UsersRepository userRepository;

    public UserRecommender(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> recommendUsers(Users user, Map<String, Integer> communities) {
        int userCommunity = communities.get(user.getIdUser());
        return userRepository.findByCommunity(Collections.singleton(userCommunity))
                .stream()
                .filter(u -> !user.getFollows().contains(u))
                .collect(Collectors.toList());
    }
}
