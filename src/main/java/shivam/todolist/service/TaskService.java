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

    public ResponseEntity<List<Task>> showAllTasks() {
        try {
            return new ResponseEntity<>(taskDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Task>> showTaskByStatus(String status) {
        try {
            return new ResponseEntity<>(taskDao.findByStatus(status), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addTask(Task task) {
        try {
            taskDao.save(task);
            return new ResponseEntity<>("Task Added", HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> deleteTask(Integer id) {
        try {
            taskDao.deleteById(id);
            return new ResponseEntity<>("Task Deleted", HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateTask(Integer id, Task task) {
        try {
            if(!taskDao.existsById(id)) {
                return new ResponseEntity<>("Task Not Found", HttpStatus.NOT_FOUND);
            }

            task.setId(id);
            taskDao.save(task);

            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Task>> showTaskByPriority(String priority) {
        try {
            return new ResponseEntity<>(taskDao.findByPriority(priority), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Task>> showTaskByCategory(String category) {
        try {
            return new ResponseEntity<>(taskDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Task>> gettodaytasks() {
        try {
            LocalDate today = LocalDate.now();
            List<Task> tasks = taskDao.findByDate(today);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<TaskStatsDTO> getStats() {
        TaskStatsDTO stats = new TaskStatsDTO();

        stats.setTotal(taskDao.count());
        stats.setCompleted(taskDao.countByStatus("Completed"));
        stats.setPending(taskDao.countByStatus("Pending"));
        stats.setInProgress(taskDao.countByStatus("In Progress"));

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
