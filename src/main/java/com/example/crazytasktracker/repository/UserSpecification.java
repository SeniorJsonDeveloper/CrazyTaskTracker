package com.example.crazytasktracker.repository;

import com.example.crazytasktracker.models.entities.User;
import com.example.crazytasktracker.models.filter.UserFilter;
import org.springframework.data.jpa.domain.Specification;

public interface UserSpecification {
    static Specification<User> withFilter(UserFilter userFilter){
        return Specification.where(byUserName(userFilter.getPhoneNumber()))
                .and(byPhoneNumber(userFilter.getPhoneNumber()));
    };
    static Specification<User> byUserName(String username){
        return (root, query, criteriaBuilder) -> {
            if (username==null){
                return null;
            }
            return criteriaBuilder.equal(root.get("username"),username);
        };
    }
    static Specification<User> byPhoneNumber(String phoneNumber){
        return (root, query, criteriaBuilder) -> {
            if (phoneNumber==null){
                return null;
            }
            return criteriaBuilder.equal(root.get("phone_number"),phoneNumber);
        };
    }
}
