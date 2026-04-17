package shivam.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shivam.todolist.model.Task;
import shivam.todolist.model.TaskStatsDTO;
import shivam.todolist.service.TaskService;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> showAllTasks() {
       List<Task> tasks = taskService.showAllTasks();
       return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks/status/{status}")
    public ResponseEntity<List<Task>> showTaskByStatus(@PathVariable String status) {
        List<Task> tasks = taskService.showTaskByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks/priority/{priority}")
    public ResponseEntity<List<Task>> showTaskByPriority(@PathVariable String priority) {
        List<Task> tasks = taskService.showTaskByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks/category/{category}")
    public ResponseEntity<List<Task>> showTaskByCategory(@PathVariable String category) {
        List<Task> tasks = taskService.showTaskByCategory(category);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/addtask")
    public ResponseEntity<String> addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return ResponseEntity.ok("Task Added");
    }

    @DeleteMapping("/deletetask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task Deleted");
    }

    @PutMapping("/updatetask/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Integer id, @RequestBody Task task) {
        String message = taskService.updateTask(id, task);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/todaytasks")
    public ResponseEntity<List<Task>> gettodaytasks() {
        List<Task> tasks = taskService.gettodaytasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/task/stats")
    public ResponseEntity<TaskStatsDTO> getStats() {
        TaskStatsDTO status = taskService.getStats();
        return ResponseEntity.ok(status);
    }
}
