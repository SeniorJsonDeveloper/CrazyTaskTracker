package com.example.crazytasktracker.models.mappers.impl;

import com.example.crazytasktracker.models.entities.User;
import com.example.crazytasktracker.models.mappers.TaskMapper;
import com.example.crazytasktracker.models.mappers.UserMapper;
import com.example.crazytasktracker.models.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final TaskMapper taskMapper;


    @Override
    public User requestToUser(UserRequest request) {
        User user = new User();
        user.setPhoneNumber(request.getPhoneNumber());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
//        user.setRoles(request.getRoles());
        return user;
    }

    @Override
    public User requestToUser(Long id, UserRequest request) {
        User user = requestToUser(request);
        user.setId(id);
        return user;
    }

    @Override
    public UserResponse response(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setUsername(user.getUsername());
        userResponse.setRoles(user.getRoles());
        userResponse.setTasks(taskMapper.responseListToList(
                user.getTasks()));
        return userResponse;
    }

    @Override
    public UserResponseList responseList(List<User> users) {
        UserResponseList userResponseList = new UserResponseList();
        userResponseList.setUserTasks(
                users.stream().map(this::response)
                        .collect(Collectors.toList())
        );
        return userResponseList;
    }

    @Override
    public List<UserResponse> responseListToList(List<User> users) {
        return users.stream().map(this::response).collect(Collectors.toList());
    }
}
