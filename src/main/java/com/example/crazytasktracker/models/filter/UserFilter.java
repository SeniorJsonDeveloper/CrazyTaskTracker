package com.example.crazytasktracker.models.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFilter {
    private Integer pageNumber;
    private Integer pageSize;
    private String username;
    private String phoneNumber;
}
