package com.example.crazytasktracker.controller;

import com.example.crazytasktracker.models.filter.TaskFilter;
import com.example.crazytasktracker.models.mappers.TaskMapper;
import com.example.crazytasktracker.models.dto.TaskRequest;
import com.example.crazytasktracker.models.dto.TaskResponse;
import com.example.crazytasktracker.models.dto.TaskResponseList;
import com.example.crazytasktracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tasks")
public class TaskController {
    private final TaskService taskServiceImpl;
    private final TaskMapper taskMapper;


    @GetMapping
    public ResponseEntity<TaskResponseList> findAll(){
        return ResponseEntity.ok(taskMapper.responseList(taskServiceImpl.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(taskMapper.response(taskServiceImpl.findById(id)));
    }
    @GetMapping("/filter")
    public ResponseEntity<TaskResponseList> findByFilter(TaskFilter filter){
        return ResponseEntity.ok(taskMapper.responseList(taskServiceImpl.findWithFilter(filter)));
    }
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request){
        var newTask = taskServiceImpl.create(taskMapper.requestToTask(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskMapper.response(taskServiceImpl.create(newTask)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id,
                                                   @RequestBody TaskRequest request){
        var existedTask = taskServiceImpl.update(taskMapper.requestToTask(id,request));
        return ResponseEntity.ok(taskMapper.response(taskServiceImpl.update(existedTask)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long id){
        taskServiceImpl.deleteById(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }




}
