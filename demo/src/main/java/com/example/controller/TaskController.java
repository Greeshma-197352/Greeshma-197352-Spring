package com.example.controller;

import com.example.entity.Task;
import com.example.repository.TaskRepository;
import com.example.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public ApiResponse<List<Task>> getAllTasks() {
        return new ApiResponse<>(true, "Tasks fetched", taskRepository.findAll());
    }

    @PostMapping
    public ApiResponse<Task> createTask(@RequestBody Task task) {
        return new ApiResponse<>(true, "Task created", taskRepository.save(task));
    }
}