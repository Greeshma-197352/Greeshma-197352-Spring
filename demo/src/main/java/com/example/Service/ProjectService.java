package com.example.service;

import com.example.dto.ProjectDto;
import com.example.entity.Project;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.AppMapper;
import com.example.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final AppMapper mapper = AppMapper.INSTANCE;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public ProjectDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        return mapper.toDto(project);
    }

    public ProjectDto createProject(ProjectDto dto) {
        return mapper.toDto(projectRepository.save(mapper.toEntity(dto)));
    }

    public ProjectDto updateProject(Long id, ProjectDto dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        return mapper.toDto(projectRepository.save(project));
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        projectRepository.delete(project);
    }
}