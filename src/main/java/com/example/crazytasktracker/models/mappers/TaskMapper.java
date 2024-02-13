package com.example.crazytasktracker.models.mappers;

import com.example.crazytasktracker.models.entities.Task;
import com.example.crazytasktracker.models.dto.TaskRequest;
import com.example.crazytasktracker.models.dto.TaskResponse;
import com.example.crazytasktracker.models.dto.TaskResponseList;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    Task requestToTask(TaskRequest request);
    Task requestToTask(Long id, TaskRequest request);
    TaskResponse response(Task task);
    TaskResponseList responseList(List<Task> tasks);
    List<TaskResponse> responseListToList(List<Task> tasks);

}
