package com.example.crazytasktracker.repository;

import com.example.crazytasktracker.models.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    Optional<Task> findByDescription(String description);
    Optional<Task> findTaskEntityByRequireDate(String requireDate);
    Page<Task> findAllByTaskName(String taskName, Pageable pageable);
}
