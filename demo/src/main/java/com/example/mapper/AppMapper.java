package com.example.mapper;

import com.example.dto.*;
import com.example.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppMapper {

    AppMapper INSTANCE = Mappers.getMapper(AppMapper.class);

    // User mapping
    UserDto toDto(User user);
    User toEntity(UserDto dto);

    // Project mapping
    ProjectDto toDto(Project project);
    Project toEntity(ProjectDto dto);

    // Task mapping
    TaskDto toDto(Task task);
    Task toEntity(TaskDto dto);

    // Comment mapping
    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto dto);
}