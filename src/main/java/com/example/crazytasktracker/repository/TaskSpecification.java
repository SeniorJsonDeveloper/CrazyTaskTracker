package com.example.crazytasktracker.repository;

import com.example.crazytasktracker.models.entities.Task;
import com.example.crazytasktracker.models.filter.TaskFilter;
import org.springframework.data.jpa.domain.Specification;

public interface TaskSpecification {
    static Specification<Task> withFilter(TaskFilter taskFilter) {
        return Specification.where(byTaskName(taskFilter.getTaskName()))
                .and(byRequireDate(taskFilter.getRequireDate()));
    }

    static Specification<Task> byTaskName(String taskName) {
        return (root, query, criteriaBuilder) -> {
            if (taskName == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("taskname"), taskName);

        };
    }
    static Specification<Task> byRequireDate(String requireDate){
        return (root, query, criteriaBuilder) -> {
            if (requireDate==null){
                return null;
            }
            return criteriaBuilder.equal(root.get("deadline"),requireDate);
        };
    }
}
