package co.develhope.task.service;

import co.develhope.task.entity.Task;
import co.develhope.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public ArrayList<Task> selectAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        return new ArrayList<>(tasks);
    }

    public Optional<Task> updateTask(Long id, Task updateTask){
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()){
            taskOptional.get().setTitle(updateTask.getTitle());
            taskOptional.get().setDescription(updateTask.getDescription());
            taskOptional.get().setDueDate(updateTask.getDueDate());
            taskOptional.get().setCompleted(updateTask.isCompleted());
            Task task = taskRepository.save(updateTask);
            return Optional.of(task);
        } else {
            return Optional.empty();
        }
    }

    public Task deleteTask(Task task){
       taskRepository.delete(task);
       return task;
    }

    /**
     *
     * @param id
     * @return oggetto salvato con campo completed = true
     */
    public Optional<Task> updateCompletedStatusTrue(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            taskOptional.get().setCompleted(true);
            Task task = taskRepository.save(taskOptional.get());
            return Optional.of(task);
        } else {
            return Optional.empty();
        }
    }
}
