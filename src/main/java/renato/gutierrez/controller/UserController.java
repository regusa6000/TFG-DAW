package renato.gutierrez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import renato.gutierrez.controller.dto.UserDTO;
import renato.gutierrez.entity.User;
import renato.gutierrez.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(path = "/user")
public class UserController {
	
	//Declarar la variable
	@Autowired
	private UserService loginService;

	@PostMapping
	public User register(@RequestBody User user) {
		return loginService.insert(user);
	}
	
	@PutMapping
	public UserDTO loginUpdate(@RequestBody UserDTO userUpdate) {
		return loginService.updateLogin(userUpdate);
	}
	
	@DeleteMapping
	public String deleteUser(@RequestBody User user) {
		return loginService.deleteUser(user.getId());
	}
}
