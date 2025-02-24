package co.develhope.task.repository;

import co.develhope.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "select * from Task task where task.completed = false and task.due_date < now()", nativeQuery = true)
    Collection<Task> taskAsLate();
}
