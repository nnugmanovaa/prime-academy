package com.example.pmcourse.controller;

import com.example.pmcourse.model.Task;
import com.example.pmcourse.model.dto.TaskUpdateDto;
import com.example.pmcourse.repository.TaskRepository;
import com.example.pmcourse.service.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("tasks")
public class TaskController {
    private ITaskService taskService;
    private TaskRepository taskRepository;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody TaskUpdateDto dto) {
        return taskService.updateTask(id, dto);
    }

    @DeleteMapping("{id}")
    public String deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

    @PatchMapping("{id}:mark-as-done")
    public void markAsDone(@PathVariable Long id) {
        taskService.markTaskAsDone(id);
    }
}
