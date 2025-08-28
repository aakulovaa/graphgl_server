package com.levent.demo.graph;

import com.levent.demo.models.Users;
import com.levent.demo.repository.UsersRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRecommender {
    private final UsersRepository userRepository;

    public UserRecommender(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> recommendUsers(Users user, Map<String, Integer> communities) {
        int userCommunity = communities.get("user" + user.getIdUser());

        // Получаем пользователей из того же сообщества
        List<Users> communityUsers = userRepository.findByCommunity(Set.of(userCommunity));

        // Фильтруем тех, на кого пользователь уже подписан
        return communityUsers.stream()
                .filter(u -> !user.getFollowing().contains(u))
                .filter(u -> !u.getIdUser().equals(user.getIdUser()))
                .toList();
    }
}
