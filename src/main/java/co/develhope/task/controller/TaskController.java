package co.develhope.task.controller;

import co.develhope.task.entity.Task;
import co.develhope.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
}
