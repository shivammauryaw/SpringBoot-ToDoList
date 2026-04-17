package shivam.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import shivam.todolist.dao.TaskDao;
import shivam.todolist.model.Task;
import shivam.todolist.model.TaskStatsDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskDao taskDao;

    public List<Task> showAllTasks() {
        return taskDao.findAll();
    }

    public List<Task> showTaskByStatus(String status) {
        return taskDao.findByStatus(status);
    }

    public Task addTask(Task task) {
        return taskDao.save(task);
    }


    public void deleteTask(Integer id) {
        taskDao.deleteById(id);
    }

    public String updateTask(Integer id, Task task) {
            if(!taskDao.existsById(id)) {
                return("Task Not Found");
            }

            task.setId(id);
            taskDao.save(task);

            return("Updated");
    }

    public List<Task> showTaskByPriority(String priority) {
        return taskDao.findByPriority(priority);
    }

    public List<Task> showTaskByCategory(String category) {
        return taskDao.findByCategory(category);
    }

    public List<Task> gettodaytasks() {
            LocalDate today = LocalDate.now();
            List<Task> tasks = taskDao.findByDate(today);
            return tasks;
    }

    public TaskStatsDTO getStats() {
        TaskStatsDTO stats = new TaskStatsDTO();

        stats.setTotal(taskDao.count());
        stats.setCompleted(taskDao.countByStatus("Completed"));
        stats.setPending(taskDao.countByStatus("Pending"));
        stats.setInProgress(taskDao.countByStatus("In Progress"));

        return stats;
    }
}
