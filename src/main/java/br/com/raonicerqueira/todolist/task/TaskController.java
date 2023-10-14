package br.com.raonicerqueira.todolist.task;

import br.com.raonicerqueira.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        taskModel.setIdUser((UUID)request.getAttribute("idUser"));

        LocalDateTime currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início / data de término deve ser maior do que a data atual");
        }

        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor do que a data de término");
        }

        TaskModel task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<TaskModel> list (HttpServletRequest request){
        List<TaskModel> tasks = this.taskRepository.findByIdUser((UUID) request.getAttribute("idUser"));
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity update (@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){
        UUID idUser = (UUID)request.getAttribute("idUser");

        var task = this.taskRepository.findById(id).orElse(null);

        if (task == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encontrada");
        }

        if (!task.getIdUser().equals(request.getAttribute("idUser"))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não tem permissão para alterar essa tarefa");
        }

        Utils.copyNonNullProperties(taskModel, task);

        return ResponseEntity.ok().body(this.taskRepository.save(task));
    }
}
