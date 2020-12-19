package com.example.pmcourse.service;

import com.example.pmcourse.model.dto.TaskCreateDto;
import com.example.pmcourse.model.entities.Task;
import com.example.pmcourse.model.dto.TaskUpdateDto;

import java.util.List;

public interface ITaskService {
    Task addTask(TaskCreateDto dto);

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task updateTask(Long id, TaskUpdateDto dto);

    String deleteTask(Long id);

    void markTaskAsDone(Long id);
}
