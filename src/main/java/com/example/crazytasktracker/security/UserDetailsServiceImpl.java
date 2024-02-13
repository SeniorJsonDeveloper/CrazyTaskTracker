package com.example.crazytasktracker.security;

import com.example.crazytasktracker.models.entities.User;
import com.example.crazytasktracker.exception.NotFoundException;
import com.example.crazytasktracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new NotFoundException("User not found. Username us : "+username));
        return new ApplicationUserDetails(user);
    }
}
