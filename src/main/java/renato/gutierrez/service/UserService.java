package renato.gutierrez.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import renato.gutierrez.controller.dto.UserDTO;
import renato.gutierrez.entity.TelegramUsers;
import renato.gutierrez.entity.User;
import renato.gutierrez.repository.TelegramUserRepository;
import renato.gutierrez.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TelegramUserRepository telegramUserRepository;
	
	//Registrar un Usuario
	public User insert(User user) {
		userRepository.save(user);
		TelegramUsers telegram = new TelegramUsers();
		telegram.setUser(user);
		telegram.setConfirmUuid(UUID.randomUUID().toString());
		telegramUserRepository.save(telegram);
		user.setTelegramUser(telegram);
		return user;
	}
	
	//Verificar Email
	public Optional<User> findEmailAndPassword(String email, String password) {
		return userRepository.emailandPassword(email, password);
	}
	
	//Actualizar Datos Usuario
	public UserDTO updateLogin(UserDTO dto) {
		Optional<User> optLogin = userRepository.findById(dto.getId());
		
		if(optLogin.isPresent()) {
			User login = optLogin.get();
			login.setEmail(dto.getEmail());
			login.setLastname(dto.getLastname());
			login.setName(dto.getName());
			login.setPassword(dto.getPassword());
			login.setTelephone(dto.getTelephone());
			
			userRepository.save(login);
			return dto;
		}else {
			return null;
		}
	}
	
	//Eliminar Usuario
	public String deleteUser(Long id){
		userRepository.deleteById(id);
		String message = "Ha salido todo bien";
		return message;
	}
	
}
