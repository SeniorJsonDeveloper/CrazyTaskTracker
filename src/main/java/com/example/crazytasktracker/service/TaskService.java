package com.example.crazytasktracker.service;

import com.example.crazytasktracker.models.entities.Task;
import com.example.crazytasktracker.models.filter.TaskFilter;

import java.util.List;


public interface TaskService {
    List<Task> findWithFilter(TaskFilter taskFilter);
    List<Task> findAll();
    Task findById(Long id);
    Task create(Task task);
    Task update(Task task);
    void deleteById(Long id);
    void deleteByIds(List<Long> ids);



}
