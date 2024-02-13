package com.example.crazytasktracker.service.impl;

import com.example.crazytasktracker.models.entities.Task;
import com.example.crazytasktracker.models.filter.TaskFilter;
import com.example.crazytasktracker.repository.TaskRepository;
import com.example.crazytasktracker.repository.TaskSpecification;
import com.example.crazytasktracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<Task> findWithFilter(TaskFilter taskFilter) {
        return taskRepository.findAll(TaskSpecification.withFilter(taskFilter),
                PageRequest.of(taskFilter.getPageNumber(),taskFilter.getPageSize()))
                .getContent();
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow (()->new RuntimeException("Task with this \"%s\" not found!"));
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);

    }

    @Override
    public Task update(Task task) {
        Task taskEntity = findById(task.getId());
       return taskRepository.save(taskEntity);


    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        taskRepository.deleteAllById(ids);
    }
}
