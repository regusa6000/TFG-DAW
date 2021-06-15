package renato.gutierrez.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import renato.gutierrez.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query(value = "select task from Task task where task.user.id = :id_user")
	List<Task> buscarTaskPorUser(@Param("id_user") Long id_user);
	
	@Query(value = "select dateStatus from Task dateStatus where dateStatus.user.id = :id_user and dateStatus.taskDate > :time")
	List<Task> findByIdUserAndActive(@Param("id_user") Long id_user,@Param("time") LocalDateTime time);
	
	@Query(value = "select dateStatus from Task dateStatus where dateStatus.user.id = :id_user and dateStatus.taskDate < :time")
	List<Task> findByIdUserAndInactive(@Param("id_user") Long id_user,@Param("time") LocalDateTime time);
	
	@Query(value = "SELECT task FROM Task task WHERE task.taskDate = :now")
	List<Task> findByTimeNow(@Param("now") LocalDateTime now);
 }
