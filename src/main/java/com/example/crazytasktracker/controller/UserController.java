package com.example.crazytasktracker.controller;

import com.example.crazytasktracker.exception.AlreadyExistsException;
import com.example.crazytasktracker.models.filter.UserFilter;
import com.example.crazytasktracker.models.mappers.UserMapper;
import com.example.crazytasktracker.models.dto.UserRequest;
import com.example.crazytasktracker.models.dto.UserResponse;
import com.example.crazytasktracker.models.dto.UserResponseList;
import com.example.crazytasktracker.repository.UserRepository;
import com.example.crazytasktracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapperImpl;
    private final UserService userService;
    private final UserRepository userRepository;
    @GetMapping
    public ResponseEntity<UserResponseList> getAll(){
        return ResponseEntity.ok(userMapperImpl.responseList(userService.findAll()));
    }
    @GetMapping("/filter")
    public ResponseEntity<UserResponseList> getWithFilter(@RequestBody UserFilter userFilter){
      return  ResponseEntity.ok(userMapperImpl.responseList(userService.findByFilter(userFilter)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(userMapperImpl.response(userService.findById(id)));
    }
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new AlreadyExistsException("User with this email already registered!");
        }
        var newUser = userService.createUser(userMapperImpl.requestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapperImpl.response(newUser));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @RequestBody UserRequest request){
        var existedUser = userService.updateUser(userMapperImpl.requestToUser(id,request));
        return ResponseEntity.ok(userMapperImpl.response(userService.updateUser(existedUser)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
