package renato.gutierrez.cron;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import renato.gutierrez.entity.Task;
import renato.gutierrez.repository.TaskRepository;
import renato.gutierrez.service.TelegramBot;

@Component
public class CheckTaskCron {
	
	@Autowired
	private TelegramBot telegramBot;
	
	@Autowired
	private TaskRepository taskRepository;

	//Le especificamos que el metodo se use cada minuto en el segundo 0
	@Scheduled(cron = "0 * * * * *")
	public void prueba(){
		List<Task> tasks = taskRepository.findByTimeNow(LocalDateTime.now());
		for(Task task : tasks) {
			sendTaskMessage(task);
		}
	}

	private void sendTaskMessage(Task task) {
		String msg = "Hola tienes un recordatorio: \n" + task.getNameTask() + "\n" + task.getDescription();
		telegramBot.sendTelegramMessage(task.getUser().getTelegramUser().getChatId(), msg, false);
	}
	
}

