package renato.gutierrez.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import renato.gutierrez.controller.dto.SubscriptionUserDTO;
import renato.gutierrez.entity.RssFeed;
import renato.gutierrez.entity.SubscriptionRss;
import renato.gutierrez.service.RssService;

@RestController
@CrossOrigin
@RequestMapping(path = "/rss")
public class RssController {

	@Autowired
	private RssService rssService;

	@GetMapping
	public List<RssFeed> rssFeed() {
		return rssService.listRss();
	}

	@GetMapping(path = "/user/{idUser}")
	public List<SubscriptionUserDTO> findSubscriptionByIdUser(@PathVariable("idUser") Long idUser) {
		List<SubscriptionRss> subscripted = rssService.findSubscriptionByIdUser(idUser);
		List<RssFeed> rssList = rssService.listRss();
		List<SubscriptionUserDTO> response = new ArrayList<>();
		for (RssFeed rssFeed : rssList) {
			SubscriptionUserDTO dto = new SubscriptionUserDTO();
			for(SubscriptionRss subscriptionRss : subscripted) {
				if(subscriptionRss.getRssFeed().getId().equals(rssFeed.getId())) {
					dto.setIdRss(rssFeed.getId());
					dto.setTitle(rssFeed.getTitulo());
					dto.setIsSubscribed(true);
					
				}
			}
			if(dto.getIdRss() == null) {
				
				dto.setIdRss(rssFeed.getId());
				dto.setTitle(rssFeed.getTitulo());
				dto.setIsSubscribed(false);
			}
			response.add(dto);
		}
		return response;
	}

	@PutMapping(path = "/subscribe/{idRss}/user/{idUser}/active/{isActive}")
	public void subscribe(@PathVariable("idRss") Long idRss, @PathVariable("idUser") Long idUser,
			@PathVariable("isActive") Boolean isActive) {
		rssService.subscribe(idRss, idUser, isActive);
	}
}

