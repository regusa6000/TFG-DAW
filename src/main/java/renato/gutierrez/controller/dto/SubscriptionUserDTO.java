package renato.gutierrez.controller.dto;

public class SubscriptionUserDTO {

	private Long idRss;
	private String title;
	private Boolean isSubscribed;

	public Long getIdRss() {
		return idRss;
	}

	public void setIdRss(Long idRss) {
		this.idRss = idRss;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsSubscribed() {
		return isSubscribed;
	}

	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

}
