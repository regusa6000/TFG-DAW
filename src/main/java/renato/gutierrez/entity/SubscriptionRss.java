package renato.gutierrez.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "subscription_rss")
public class SubscriptionRss {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@JsonIgnore
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "id_rss_feed", nullable = false)
	private RssFeed rssFeed;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public RssFeed getRssFeed() {
		return rssFeed;
	}
	public void setRssFeed(RssFeed rssFeed) {
		this.rssFeed = rssFeed;
	}
	
	

}
