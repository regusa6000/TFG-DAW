package renato.gutierrez.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import renato.gutierrez.entity.TelegramUsers;
import renato.gutierrez.repository.TelegramUserRepository;

@Component
public class TelegramBot extends TelegramLongPollingBot {

	@Autowired
	private TelegramUserRepository telegramRepository;
	
	//Para hacer una lista 
	private List<Long> chatIds;
	
	@PostConstruct
	private void initLoad() {
		actualziarChatIds();
	}
	
	private void actualziarChatIds() {
		chatIds = telegramRepository.obtenerChatIds();
	}

	//Este metodo se lanza cuando envian un mensaje
	@Override
	public void onUpdateReceived(Update update) {
		Long chatId = update.getMessage().getChatId();
		if (chatIds.contains(chatId)) {
			sendTelegramMessage(chatId, "Su cuenta ya se encuentra validada. Acceda a la seccion de tareas", false);
		} else {
			// No es un usuario sincronizado
			sincronizarUsuario(chatId, update.getMessage().getText());
		}
	}

	private void sincronizarUsuario(Long chatId, String uuid) {
		Optional<TelegramUsers> optTeleUser = telegramRepository.buscarPorUuid(uuid);
		if(optTeleUser.isPresent()) {
			 TelegramUsers telegramUser = optTeleUser.get();
			 telegramUser.setChatId(chatId);
			 telegramRepository.save(telegramUser);
			 sendTelegramMessage(chatId, "Su cuenta ha sido validada correctamente", false);
			 sendTelegramMessage(chatId, "Bienvenido " + telegramUser.getUser().getName() + " " + telegramUser.getUser().getLastname(), false);
			 sendTelegramMessage(chatId, "Cierre SesiÃ³n y vuelva a Loguearse.", false);
			 actualziarChatIds();
		} else {
			sendTelegramMessage(chatId, "UUID no valido", false);
			sendTelegramMessage(chatId, "Vuelve a introducir el UUID", false);
		}
	}
	
	public void sendTelegramMessage(Long chatId, String msg, boolean withHtml) {
		SendMessage respuesta = SendMessage.builder()
        .chatId(String.valueOf(chatId))
        .text(msg)
        .build();
		respuesta.enableHtml(withHtml);
        try {
            execute(respuesta);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}
	

	//Nombre del bot de telegram 
	@Override
	public String getBotUsername() {
		return "tfg_renato_bot";
	}

	//Token generado del Bot de Telegram
	@Override
	public String getBotToken() {
		return "1826595055:AAGYs6OsT0eXEnPVXaVxh40Y_k_fEb2wPTA";
	}
	
}
