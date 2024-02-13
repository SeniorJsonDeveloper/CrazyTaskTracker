package com.example.crazytasktracker.models.mappers;

import com.example.crazytasktracker.models.entities.User;
import com.example.crazytasktracker.models.dto.UserRequest;
import com.example.crazytasktracker.models.dto.UserResponse;
import com.example.crazytasktracker.models.dto.UserResponseList;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User requestToUser(UserRequest request);
    User requestToUser(Long id,UserRequest request);
    UserResponse response(User user);
    UserResponseList responseList(List<User> users);
    List<UserResponse> responseListToList(List<User> users);
}
