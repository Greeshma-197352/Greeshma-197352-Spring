package com.example.service;

import com.example.dto.TaskDto;
import com.example.entity.Task;
import com.example.entity.User;
import com.example.entity.Project;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.AppMapper;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import com.example.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final AppMapper mapper = AppMapper.INSTANCE;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        return mapper.toDto(task);
    }

    public TaskDto createTask(TaskDto dto) {
        Task task = mapper.toEntity(dto);
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));
            task.setUser(user);
        }
        if (dto.getProjectId() != null) {
            Project project = projectRepository.findById(dto.getProjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + dto.getProjectId()));
            task.setProject(project);
        }
        return mapper.toDto(taskRepository.save(task));
    }

    public TaskDto updateTask(Long id, TaskDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));
            task.setUser(user);
        }

        if (dto.getProjectId() != null) {
            Project project = projectRepository.findById(dto.getProjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + dto.getProjectId()));
            task.setProject(project);
        }

        return mapper.toDto(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        taskRepository.delete(task);
    }
}