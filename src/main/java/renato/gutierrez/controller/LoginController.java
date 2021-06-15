package renato.gutierrez.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import renato.gutierrez.controller.dto.UserDTO;
import renato.gutierrez.entity.User;
import renato.gutierrez.service.UserService;

@RestController
@RequestMapping(path = "/login")
@CrossOrigin
public class LoginController {

	//Declarar la variable
		@Autowired
		private UserService loginService;

		@PostMapping
		public UserDTO login(@RequestBody UserDTO user) {
			Optional<User> login = loginService.findEmailAndPassword(user.getEmail(), user.getPassword());
			
			if(login.isPresent()) {
				
				User respuestaLogin = login.get();
				UserDTO dto = new UserDTO();
				dto.setEmail(respuestaLogin.getEmail());
				dto.setId(respuestaLogin.getId());
				dto.setLastname(respuestaLogin.getLastname());
				dto.setName(respuestaLogin.getName());
				dto.setPassword(respuestaLogin.getPassword());
				dto.setTelephone(respuestaLogin.getTelephone());
				dto.setToken(getJWTToken(user.getEmail()));
				dto.setUuid(login.get().getTelegramUser().getConfirmUuid());
				dto.setValidated(login.get().getTelegramUser().getChatId() != null);
				
				return dto;
			}else {
				return null;
			}
		}
		
	
		private String getJWTToken(String username) {
			String secretKey = "mySecretKey";
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_USER");
			
			String token = Jwts
					.builder()
					.setId("Renato")
					.setSubject(username)
					.claim("authorities",
							grantedAuthorities.stream()
									.map(GrantedAuthority::getAuthority)
									.collect(Collectors.toList()))
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 600000))
					.signWith(SignatureAlgorithm.HS512,
							secretKey.getBytes()).compact();

			return "Bearer " + token;
		}
	
}
