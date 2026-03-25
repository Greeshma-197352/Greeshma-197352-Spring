package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Long userId;     // To associate with User
    private Long projectId;  // To associate with Project
}