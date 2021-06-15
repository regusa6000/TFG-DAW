package renato.gutierrez.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import renato.gutierrez.entity.User;

@Repository
public interface UserRepository extends JpaRepository< User, Long>{

	@Query(value = "select l from User l where l.email = :email")
	Optional<User> buscarPorEmail(@Param("email") String email);
	
	@Query(value = "select user from User user where user.email = :email and user.password = :password")
	Optional<User> emailandPassword(@Param("email") String email , @Param("password") String password);
	
}
