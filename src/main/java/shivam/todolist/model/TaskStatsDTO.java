package shivam.todolist.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class TaskStatsDTO {

    private long total;
    private long completed;
    private long pending;
    private long inProgress;
}
