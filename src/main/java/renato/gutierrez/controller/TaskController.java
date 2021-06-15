package renato.gutierrez.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import renato.gutierrez.controller.dto.TaskDTO;
import renato.gutierrez.entity.Task;
import renato.gutierrez.service.TaskService;

@RestController
@CrossOrigin
@RequestMapping(path = "/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping
	public Task registerTask(@RequestBody TaskDTO register) {
		return taskService.insert(register);
	}
	
	@DeleteMapping(path = "/{id_task}")
	public String deleteTask(@PathVariable("id_task") Long id_task) {
		return taskService.deleteTask(id_task);
	}
	
	@GetMapping
	public List<Task> taskStatus(@RequestParam("id_user") Long id_user,@RequestParam Optional<Boolean> activo){
		if(activo.isPresent()) {
			return taskService.statusTask(id_user,activo.get());
		}else {
			return taskService.taskForUser(id_user);
		}
	}
	
	@PutMapping
	public TaskDTO taskUpdate(@RequestBody TaskDTO objectUpdate) {
		return taskService.updateTask(objectUpdate);
	}
}
