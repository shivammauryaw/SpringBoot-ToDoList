package shivam.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shivam.todolist.model.Task;
import shivam.todolist.service.TaskService;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/showall")
    public ResponseEntity<List<Task>> showAllTasks() {
        return taskService.showAllTasks();
    }

    @GetMapping("/showbystatus/{status}")
    public ResponseEntity<List<Task>> showTaskByStatus(@PathVariable String status) {
        return taskService.showTaskByStatus(status);
    }

    @GetMapping("/showbypriority/{priority}")
    public ResponseEntity<List<Task>> showTaskByPriority(@PathVariable String priority) {
        return taskService.showTaskByPriority(priority);
    }

    @GetMapping("/showbycategory/{category}")
    public ResponseEntity<List<Task>> showTaskByCategory(@PathVariable String category) {
        return taskService.showTaskByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @DeleteMapping("/deletetask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer id) {
        return taskService.deleteTask(id);
    }

    @PutMapping("/updatetask/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Integer id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @GetMapping("/todaytasks")
    public ResponseEntity<List<Task>> gettodaytasks() {
        return taskService.gettodaytasks();
    }
}
