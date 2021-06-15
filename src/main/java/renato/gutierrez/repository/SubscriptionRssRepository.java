package renato.gutierrez.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import renato.gutierrez.entity.SubscriptionRss;

public interface SubscriptionRssRepository extends JpaRepository<SubscriptionRss, Long>{
	
	@Query("SELECT sub FROM SubscriptionRss sub WHERE sub.user.id = :idUser")
	List<SubscriptionRss> findByIdUser(@Param("idUser") Long idUser);
	
	@Query("SELECT sub FROM SubscriptionRss sub WHERE sub.user.id = :idUser and sub.rssFeed.id = :idRss")
	Optional<SubscriptionRss> findByIdUserAndIdRss(@Param("idUser") Long idUser, @Param("idRss") Long idRss);
	
	@Query("SELECT sub FROM SubscriptionRss sub WHERE sub.rssFeed.id = :idRss")
	List<SubscriptionRss> findByRssId(@Param("idRss") Long idRss);

}
