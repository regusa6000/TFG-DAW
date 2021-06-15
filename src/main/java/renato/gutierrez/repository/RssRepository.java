package renato.gutierrez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import renato.gutierrez.entity.RssFeed;

public interface RssRepository extends JpaRepository<RssFeed, Long> {

}
