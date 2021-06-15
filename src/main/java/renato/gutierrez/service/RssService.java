package renato.gutierrez.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import renato.gutierrez.entity.RssFeed;
import renato.gutierrez.entity.SubscriptionRss;
import renato.gutierrez.entity.User;
import renato.gutierrez.repository.RssRepository;
import renato.gutierrez.repository.SubscriptionRssRepository;
import renato.gutierrez.repository.UserRepository;

@Service
public class RssService {

	@Autowired
	private RssRepository rssRepository;

	@Autowired
	private SubscriptionRssRepository subscriptionRssRepository;

	@Autowired
	private UserRepository userRepository;

	public List<RssFeed> listRss() {
		return rssRepository.findAll();
	}

	public List<SubscriptionRss> findSubscriptionByIdUser(Long idUser) {
		return subscriptionRssRepository.findByIdUser(idUser);
	}
	
	public List<SubscriptionRss> findSubscriptionByRssId(Long rssId) {
		return subscriptionRssRepository.findByRssId(rssId);
	}
	
	public void subscribe(Long idRss, Long idUser, Boolean isActive) {
		Optional<SubscriptionRss> optSub = subscriptionRssRepository.findByIdUserAndIdRss(idUser, idRss);
		if (optSub.isPresent()) {
			SubscriptionRss subcription = optSub.get();
			if (!isActive) {
				subscriptionRssRepository.delete(subcription);
			}
		} else {
			if (isActive) {
				SubscriptionRss newSubscription = new SubscriptionRss();
				Optional<User> user = userRepository.findById(idUser);
				Optional<RssFeed> rssFeed = rssRepository.findById(idRss);
				newSubscription.setUser(user.get());
				newSubscription.setRssFeed(rssFeed.get());
				subscriptionRssRepository.save(newSubscription);
			}
		}
	}

}
