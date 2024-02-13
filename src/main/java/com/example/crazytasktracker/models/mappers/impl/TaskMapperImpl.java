package com.example.crazytasktracker.models.mappers.impl;

import com.example.crazytasktracker.models.entities.Task;
import com.example.crazytasktracker.models.mappers.TaskMapper;
import com.example.crazytasktracker.models.dto.TaskRequest;
import com.example.crazytasktracker.models.dto.TaskResponse;
import com.example.crazytasktracker.models.dto.TaskResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task requestToTask(TaskRequest request) {
        Task task = new Task();
        task.setTaskName(request.getTaskname());
        task.setDescription(request.getDescription());
        task.setRequireDate(request.getRequireDate());
        return task;

    }

    @Override
    public Task requestToTask(Long id, TaskRequest request) {
        Task task = requestToTask(request);
        task.setId(id);
        return task;
    }

    @Override
    public TaskResponse response(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTaskname(task.getTaskName());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setCreatedAt(task.getCreatedAt());
        taskResponse.setRequireDate(task.getRequireDate());
        return taskResponse;
    }

    @Override
    public TaskResponseList responseList(List<Task> tasks) {
        TaskResponseList taskResponseList = new TaskResponseList();
        taskResponseList.setTasks(
                tasks.stream()
                        .map(this::response)
                        .collect(Collectors.toList())
        );
        return taskResponseList;
    }

    @Override
    public List<TaskResponse> responseListToList(List<Task> tasks) {
        return tasks.stream().map(this::response).collect(Collectors.toList());
    }
}
