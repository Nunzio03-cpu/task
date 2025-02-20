package co.develhope.task.service;

import co.develhope.task.entity.Task;
import co.develhope.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
