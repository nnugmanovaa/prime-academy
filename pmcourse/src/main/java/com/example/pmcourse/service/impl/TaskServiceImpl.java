package com.example.pmcourse.service.impl;

import com.example.pmcourse.exception.ErrorMessageConstants;
import com.example.pmcourse.exception.GeneralApiServerException;
import com.example.pmcourse.model.dto.TaskCreateDto;
import com.example.pmcourse.model.entities.Task;
import com.example.pmcourse.model.dto.TaskUpdateDto;
import com.example.pmcourse.repository.TaskRepository;
import com.example.pmcourse.service.ITaskService;
import com.example.pmcourse.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService {
    private TaskRepository taskRepository;
    private IUserService userService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, IUserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public Task addTask(TaskCreateDto dto) {
        Long userId = userService.getCurrentUserId();
        return taskRepository.save(
                Task.builder()
                .date(dto.getDate())
                .description(dto.getDescription())
                .userId(userId)
                .build()
        );
    }

    @Override
    public List<Task> getAllTasks() {
        Long userId = userService.getCurrentUserId();
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public Task getTaskById(Long id) {
        Long userId = userService.getCurrentUserId();
        return  taskRepository.findByIdAndUserId(id,userId).orElseThrow(
                () -> new GeneralApiServerException(ErrorMessageConstants.TASK_NOT_FOUND));
    }

    @Override
    public Task updateTask(Long id, TaskUpdateDto dto) {
        Long userId = userService.getCurrentUser().getId();
        Task task = taskRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new GeneralApiServerException(ErrorMessageConstants.TASK_NOT_FOUND));
       task.setDescription(dto.getDescription());
        return taskRepository.save(task);
    }

    @Override
    public String deleteTask(Long id) {
        Long userId = userService.getCurrentUserId();
        Task task = taskRepository.findByIdAndUserId(id,userId ).orElseThrow(
                () -> new GeneralApiServerException(ErrorMessageConstants.TASK_NOT_FOUND));
        taskRepository.delete(task);
        return ErrorMessageConstants.TASK_SUCCESSFULLY_DELETED;
    }

    @Override
    public void markTaskAsDone(Long id) {
        Long userId = userService.getCurrentUserId();
        taskRepository.markAsDone(id, userId);
    }
}
