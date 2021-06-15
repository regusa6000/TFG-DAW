package renato.gutierrez.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import renato.gutierrez.controller.dto.TaskDTO;
import renato.gutierrez.entity.Task;
import renato.gutierrez.entity.User;
import renato.gutierrez.repository.TaskRepository;
import renato.gutierrez.repository.UserRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//Encontrar Tareas 
	public List<Task> taskForUser(Long id_user){
		return taskRepository.buscarTaskPorUser(id_user);
	}
	
	public Task insert(TaskDTO register) {
		Optional<User> user = userRepository.findById(register.getIdUser());
		Task task = new Task();
		task.setDescription(register.getDescription());
		task.setNameTask(register.getNameTask());
		task.setTaskDate(register.getTaskDate().plusHours(2).toLocalDateTime());
		task.setUser(user.get());
		return taskRepository.save(task);
	}
	
	public String deleteTask(Long id_task) {
		taskRepository.deleteById(id_task);
		String message = "Ha salido todo bien";
		return message;
	}
	
	/*public List<Task> TaskInProgress(Long id_user,String status){
		return taskRepository.TaskInProgress(id_user,status);
	}*/
	
	public List<Task> statusTask(Long id_user,boolean activo){
		
		if(activo) {
			return taskRepository.findByIdUserAndActive(id_user, LocalDateTime.now());
		}else {
			return taskRepository.findByIdUserAndInactive(id_user, LocalDateTime.now());
		}
	}
	
	//Actualizar Datos de Tarea
	public TaskDTO updateTask(TaskDTO dto) {
		
		Optional<Task> optTask = taskRepository.findById(dto.getId());
		
		if(optTask.isPresent()) {
			Optional<User> user = userRepository.findById(dto.getIdUser());
			Task task = optTask.get();
			task.setUser(user.get());
			task.setNameTask(dto.getNameTask());
			task.setDescription(dto.getDescription());
			task.setTaskDate(dto.getTaskDate().plusHours(2).toLocalDateTime());
			
			taskRepository.save(task);
			return dto;
		}else {
			return null;
		}
		
	}
}
