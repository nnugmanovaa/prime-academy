package com.example.pmcourse.service;

import com.example.pmcourse.model.Task;
import com.example.pmcourse.model.dto.TaskUpdateDto;

import java.util.List;

public interface ITaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task updateTask(Long id, TaskUpdateDto dto);
    String deleteTask(Long id);
    void markTaskAsDone(Long id);
}
