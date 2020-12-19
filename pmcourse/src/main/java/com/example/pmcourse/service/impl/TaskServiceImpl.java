package com.example.pmcourse.service.impl;

import com.example.pmcourse.exception.ErrorMessageConstants;
import com.example.pmcourse.exception.GeneralApiServerException;
import com.example.pmcourse.model.dto.TaskCreateDto;
import com.example.pmcourse.model.entities.Task;
import com.example.pmcourse.model.dto.TaskUpdateDto;
import com.example.pmcourse.repository.TaskRepository;
import com.example.pmcourse.service.ITaskService;
import com.example.pmcourse.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private IUserService userService;

    private Long getCurrentUserId() {
        return userService.getCurrentUser().getId();
    }

    @Override
    public Task addTask(TaskCreateDto dto) {
        Long userId = getCurrentUserId();
        Task task = Task.builder()
                .date(dto.getDate())
                .description(dto.getDescription())
                .userId(userId)
                .build();
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        Long userId = userService.getCurrentUser().getId();
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public Task getTaskById(Long id) {
        Long userId = userService.getCurrentUser().getId();
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
        Long userId = userService.getCurrentUser().getId();
        Task task = taskRepository.findByIdAndUserId(id,userId ).orElseThrow(
                () -> new GeneralApiServerException(ErrorMessageConstants.TASK_NOT_FOUND));
        taskRepository.delete(task);
        return ErrorMessageConstants.TASK_SUCCESSFULLY_DELETED;
    }

    @Override
    public void markTaskAsDone(Long id) {
        Long userId = userService.getCurrentUser().getId();
        taskRepository.markAsDone(id, userId);
    }
}
