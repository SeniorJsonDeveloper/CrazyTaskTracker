package com.example.crazytasktracker.service;

import com.example.crazytasktracker.models.entities.User;
import com.example.crazytasktracker.models.filter.UserFilter;

import java.util.List;

public interface UserService {
    List<User> findByFilter(UserFilter filter);
    List<User> findAll();
    User findById(Long id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
}
