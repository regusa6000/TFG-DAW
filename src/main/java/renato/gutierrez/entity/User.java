package renato.gutierrez.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String lastname;
	private String email;
	private String password;
	private int telephone;
	private String rol;

	@OneToOne(mappedBy = "user")
	private TelegramUsers telegramUser;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Task> tasks;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<SubscriptionRss> subscriptionRss;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public TelegramUsers getTelegramUser() {
		return telegramUser;
	}

	public void setTelegramUser(TelegramUsers telegramUser) {
		this.telegramUser = telegramUser;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<SubscriptionRss> getSubscriptionRss() {
		return subscriptionRss;
	}

	public void setSubscriptionRss(Set<SubscriptionRss> subscriptionRss) {
		this.subscriptionRss = subscriptionRss;
	}

}
