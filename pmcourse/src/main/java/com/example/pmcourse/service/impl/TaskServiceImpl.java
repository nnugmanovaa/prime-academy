package com.example.pmcourse.service.impl;

import com.example.pmcourse.exception.GeneralApiServerException;
import com.example.pmcourse.model.Task;
import com.example.pmcourse.model.dto.TaskUpdateDto;
import com.example.pmcourse.repository.TaskRepository;
import com.example.pmcourse.service.ITaskService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            throw new GeneralApiServerException("entity with id " + id  + " not found!");
        }
        return task.get();
    }

    @Override
    public Task updateTask(Long id, TaskUpdateDto dto) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            throw new GeneralApiServerException("entity with id " + id  + " not found!");
        }
       if (dto.getDescription() == null) {
           return task.get();
       }
       task.get().setDescription(dto.getDescription());
        return taskRepository.save(task.get());
    }

    @Override
    public String deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            throw new GeneralApiServerException("entity with id " + id  + " not found!");
        }
        taskRepository.delete(task.get());
        return "Task with id " + id + " successfully deleted!";
    }

    @Override
    public void markTaskAsDone(Long id) {
        taskRepository.markAsDone(id);
    }
}
