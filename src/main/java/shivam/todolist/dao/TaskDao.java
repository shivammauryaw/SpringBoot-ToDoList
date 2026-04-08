package shivam.todolist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shivam.todolist.model.Task;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task, Integer> {

    List<Task> findByStatus(String status);

    List<Task> findByPriority(String priority);

    List<Task> findByCategory(String category);

    List<Task> findByDate(LocalDate date);
}
