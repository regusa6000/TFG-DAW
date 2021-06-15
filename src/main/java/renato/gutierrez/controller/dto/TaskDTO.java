package renato.gutierrez.controller.dto;

import java.time.ZonedDateTime;

public class TaskDTO {

	private Long id;
	private Long idUser;
	private String nameTask;
	private String description;
	private ZonedDateTime taskDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getNameTask() {
		return nameTask;
	}

	public void setNameTask(String nameTask) {
		this.nameTask = nameTask;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ZonedDateTime getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(ZonedDateTime taskDate) {
		this.taskDate = taskDate;
	}

}
