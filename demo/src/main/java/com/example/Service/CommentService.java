package com.example.service;

import com.example.dto.CommentDto;
import com.example.entity.Comment;
import com.example.entity.Task;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.AppMapper;
import com.example.repository.CommentRepository;
import com.example.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final AppMapper mapper = AppMapper.INSTANCE;

    public CommentService(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    public List<CommentDto> getAllComments() {
        return commentRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + id));
        return mapper.toDto(comment);
    }

    public CommentDto createComment(CommentDto dto) {
        Comment comment = mapper.toEntity(dto);
        if (dto.getTaskId() != null) {
            Task task = taskRepository.findById(dto.getTaskId())
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + dto.getTaskId()));
            comment.setTask(task);
        }
        return mapper.toDto(commentRepository.save(comment));
    }

    public CommentDto updateComment(Long id, CommentDto dto) {
        Comment comment = commentRepository.findById(id)
    }
}