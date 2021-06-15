package renato.gutierrez.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import renato.gutierrez.entity.TelegramUsers;

public interface TelegramUserRepository extends JpaRepository<TelegramUsers, Long> {

	@Query(value = "select chatid from TelegramUsers chatid where chatid.chatId = :chat_id")
	Optional<TelegramUsers> obtenerPorChatId(@Param("chat_id") Long chat_id);
	
	@Query(value = "SELECT users.chatId FROM TelegramUsers users")
	List<Long> obtenerChatIds();
	
	@Query(value = "SELECT users FROM TelegramUsers users WHERE users.confirmUuid = :uuid") 
	Optional<TelegramUsers> buscarPorUuid(@Param("uuid") String uuid);
	
}
