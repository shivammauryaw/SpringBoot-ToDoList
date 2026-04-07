package shivam.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import shivam.todolist.dao.TaskDao;
import shivam.todolist.model.Task;

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

    public ResponseEntity<List<Task>> showCompletedTasks() {
        try {
            return new ResponseEntity<>(taskDao.findByCompleted(true), HttpStatus.OK);
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
}
