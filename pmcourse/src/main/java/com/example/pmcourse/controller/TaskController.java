package com.example.pmcourse.controller;

import com.example.pmcourse.model.dto.TaskCreateDto;
import com.example.pmcourse.model.entities.Task;
import com.example.pmcourse.model.dto.TaskUpdateDto;
import com.example.pmcourse.service.ITaskService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("api/tasks")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskCreateDto dto) {
        return ResponseEntity.ok(taskService.addTask(dto));
    }

    @GetMapping("all")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskUpdateDto dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @PatchMapping("{id}:mark-as-done")
    public void markAsDone(@PathVariable Long id) {
        taskService.markTaskAsDone(id);
    }
}
