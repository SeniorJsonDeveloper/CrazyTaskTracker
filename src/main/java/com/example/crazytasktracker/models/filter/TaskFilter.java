package com.example.crazytasktracker.models.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskFilter {
    private Integer pageNumber;
    private Integer pageSize;
    private String taskName;
    private String requireDate;
}
