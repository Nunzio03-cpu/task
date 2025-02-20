package co.develhope.task.controller;

import co.develhope.task.entity.Task;
import co.develhope.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create-task")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/select-all-tasks")
    public ResponseEntity<ArrayList<Task>> selectAllTasks(){
        return ResponseEntity.ok(taskService.selectAllTasks());
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<Optional<Task>> updateTask(@PathVariable Long id, @RequestBody Task task){
        Optional<Task> taskOptional = taskService.updateTask(id, task);
        if (taskOptional.isPresent()){
            return ResponseEntity.ok(taskOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-task")
    public ResponseEntity<Task> deleteTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.deleteTask(task));
    }
}
