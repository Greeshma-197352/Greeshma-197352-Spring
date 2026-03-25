package com.example.controller;

import com.example.entity.Project;
import com.example.repository.ProjectRepository;
import com.example.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public ApiResponse<List<Project>> getAllProjects() {
        return new ApiResponse<>(true, "Projects fetched", projectRepository.findAll());
    }

    @PostMapping
    public ApiResponse<Project> createProject(@RequestBody Project project) {
        return new ApiResponse<>(true, "Project created", projectRepository.save(project));
    }
}