package renato.gutierrez.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rss_feed")
public class RssFeed {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String titulo;
	private String url;

	
	@OneToMany(mappedBy = "rssFeed")
	@JsonIgnore
	private Set<SubscriptionRss> subscriptionRss;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<SubscriptionRss> getSubscriptionRss() {
		return subscriptionRss;
	}

	public void setSubscriptionRss(Set<SubscriptionRss> subscriptionRss) {
		this.subscriptionRss = subscriptionRss;
	}

}
