package shivam.todolist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shivam.todolist.model.Task;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task, Integer> {

    List<Task> findByCompleted(boolean Completed);
}
