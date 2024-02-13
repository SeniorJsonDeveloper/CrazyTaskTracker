//package com.example.crazytasktracker.models;
//
//import com.example.crazytasktracker.entities.TaskEntity;
//import lombok.*;
//import org.springframework.stereotype.Component;
//
//import java.time.Instant;
//import java.util.UUID;
//
//@Component
//@RequiredArgsConstructor
//public class TaskDtoFactory {
//    private final TaskEntity task;
//
//
//    public TaskDto taskFactory (TaskEntity task){
//        return TaskDto.builder()
//                .id(task.getId())
//                .taskName(task.getTaskName())
//                .description(task.getDescription())
//                .createdAt(task.getCreatedAt())
//                .requireDate(task.getRequireDate())
//                .build();
//    }
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class TaskDto{
//        private UUID id;
//        private String taskName;
//        private String description;
//        private Instant createdAt;
//        private String requireDate;
//    }
//
//}
