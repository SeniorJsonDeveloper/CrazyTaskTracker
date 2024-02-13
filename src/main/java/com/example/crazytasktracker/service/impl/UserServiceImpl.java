package com.example.crazytasktracker.service.impl;

import com.example.crazytasktracker.models.entities.User;
import com.example.crazytasktracker.models.filter.UserFilter;
import com.example.crazytasktracker.repository.UserRepository;
import com.example.crazytasktracker.repository.UserSpecification;
import com.example.crazytasktracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public List<User> findByFilter(UserFilter filter) {
        return userRepository.findAll(UserSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(),filter.getPageSize())).getContent();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("пользователь с указанным id не найден!"));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
       userRepository.deleteById(id);
    }
}
