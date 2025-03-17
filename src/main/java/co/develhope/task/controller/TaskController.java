package co.develhope.task.controller;

import co.develhope.task.entity.Task;
import co.develhope.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     *  Endpoint POST /create-task
     *  Descrizione: crea un nuovo oggetto task
     *  @param task
     *  @return file json del nuovo oggetto task
     */
    @PostMapping("/create-task")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.createTask(task));
    }

    /**
     *  Endpoint GET /select-all-tasks
     *  Descrizione: seleziona tutti i task creati
     *  @return tutti gli oggetti task all'interno di un ArrayList
     */
    @GetMapping("/select-all-tasks")
    public ResponseEntity<ArrayList<Task>> selectAllTasks(){
        List<Task> tasks = taskService.selectAllTasks();
        return ResponseEntity.ok(new ArrayList<>(tasks));
    }

    /**
     *  Endpoint PUT /update-task/{id}
     *  Descrizione: cerca l'oggetto task tramite il suo id
     *  e aggiorna i suoi campi con quelli del task aggiornato
     *  @param id
     *  @param task
     *  @return task aggiornato
     */
    @PutMapping("/update-task/{id}")
    public ResponseEntity<Optional<Task>> updateTask(@PathVariable Long id, @RequestBody Task task){
        Optional<Task> taskOptional = taskService.updateTask(id, task);
        if (taskOptional.isPresent()){
            return ResponseEntity.ok(taskOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  Endpoint DELETE /delete-task
     *  Descrizione: Elimina il task inserito
     *  @param task
     *  @return oggetto task eliminato
     */
    @DeleteMapping("/delete-task")
    public ResponseEntity<Task> deleteTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.deleteTask(task));
    }

    /**
     *  Endpoint PUT /update-completed-status-true{id}
     *  Descrizione: Trova l'oggetto task tramite il suo id
     *  e ne aggiorna lo stato del suo campo completed da false a true
     *  @param id
     *  @return oggetto task aggiornato
     */
    @PutMapping("/update-completed-status-true/{id}")
    public ResponseEntity<Optional<Task>> updateCompletedStatusTrue(@PathVariable Long id){
        Optional<Task> taskOptional = taskService.updateCompletedStatusTrue(id);
        if (taskOptional.isPresent()){
            return ResponseEntity.ok(taskOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  Endpoint: GET /time-remaining
     *  Descrizione: Calcola quanto tempo rimane alla data del task
     *  @param task
     *  @return tempo rimanente al task: se non è ancora passato restituirà
     *  quanti giorni mancano, altrimenti restituirà 0
     */
    @GetMapping("/time-remaining")
    public ResponseEntity<Long> timeRemaining(@RequestBody Task task){
        return ResponseEntity.ok(taskService.timeRemainig(task));
    }

    /**
     *  Endpoint: GET /mark-task-late
     *  Descrizione: marchia i task in ritardo
     *  @return messaggio che i task sono stati marchiati
     */
    @GetMapping("/mark-task-late")
    public ResponseEntity<String> markTaskAsLate(){
        taskService.markTaskAsLate();
        return ResponseEntity.ok("Tutti i task in ritardo sono stati contrassegnati.");
    }

    /**
     *  Endpoint: GET /select-task-late
     *  Descrizione: seleziona tutti i task marchiati come "in ritardo"
     *  @return Lista con tutti i task in ritardo
     */
    @GetMapping("/select-task-late")
    public ResponseEntity<ArrayList<Task>> selectTaskAsLate(){
        ArrayList<Task> tasks = taskService.selectTaskAsLate();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/create-random-tasks")
    public ResponseEntity<String> createRandomTasks(@RequestParam int count){
        taskService.getRandomTask(count);
        return ResponseEntity.ok("Creati count tasks");
    }
}
