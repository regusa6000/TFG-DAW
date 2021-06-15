package renato.gutierrez.controller;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import renato.gutierrez.entity.User;
import renato.gutierrez.model.Prueba;
import renato.gutierrez.repository.UserRepository;

@RestController
@CrossOrigin
public class PruebaController {

	@Autowired
	private UserRepository loginRepository;
	
	@GetMapping(path = "/renato")
	public String renato() {
		return "Hola";
	}
	
	@GetMapping(path = "/json")
	public Prueba jalar() {
		
		Prueba prueba = new Prueba();
		
		prueba.setNombre("Ayrton");
		prueba.setEdad(26);
		prueba.setFecha(LocalDate.now());
		
		return prueba;
	}
	
	
	@GetMapping(path = "/consulta")
	public Optional<User> a(@RequestParam String email){
		return loginRepository.buscarPorEmail(email);
	}
	
}
