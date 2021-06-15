package renato.gutierrez.cron;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import renato.gutierrez.entity.RssFeed;
import renato.gutierrez.entity.SubscriptionRss;
import renato.gutierrez.model.ItemMarca;
import renato.gutierrez.model.RssMarca;
import renato.gutierrez.service.RssService;
import renato.gutierrez.service.TelegramBot;

@Component
public class CheckNewsCron {
	
	@Autowired
	private RssService rssService;
	
	@Autowired
	private TelegramBot telegramBot;
	
	@Value("${check.news.update.minutes}")
	private long CHECK_NEWS_MINUTES;
	
	@Scheduled(fixedRateString = "${check.news.update.milliseconds}")
	public void checkNews(){
		List<RssFeed> rssList = rssService.listRss();
		for (RssFeed rss : rssList) {
			RssMarca feed = getRssMarcaByUrl(rss.getUrl());
			List<ItemMarca> newNews = getNewNews(feed.getChannelMarca().getItems());
			sendMessages(newNews, rss.getId());
		}
	}

	private void sendMessages(List<ItemMarca> newNews, Long rssId) {
		List<SubscriptionRss> subs = rssService.findSubscriptionByRssId(rssId);
		for(ItemMarca news : newNews) {
			for(SubscriptionRss sub : subs) {
				sendNewsMessage(sub.getUser().getTelegramUser().getChatId(), news);
			}
		}
	}

	private void sendNewsMessage(Long chatId, ItemMarca news) {
		telegramBot.sendTelegramMessage(chatId, news.getTitle(), false);
		telegramBot.sendTelegramMessage(chatId, news.getLink(), false);
	}

	private List<ItemMarca> getNewNews(List<ItemMarca> items) {
		List<ItemMarca> filtered = new ArrayList<>();
		for(ItemMarca item : items) {
			//Convierto la fecha string en LocalDateTime
			LocalDateTime pubDate = ZonedDateTime.parse(item.getPubDate(), DateTimeFormatter.RFC_1123_DATE_TIME).toLocalDateTime();
			//Le aumento los minutos de actualizacion
			pubDate = pubDate.plusMinutes(CHECK_NEWS_MINUTES);
			//Si entra dentro del periodo de actualizacion lo agregamos
			if (LocalDateTime.now().isBefore(pubDate)) {
				filtered.add(item);
			}
		}
		return filtered;
	}

	private RssMarca getRssMarcaByUrl(String url) {
		RestTemplate rest = new RestTemplate();
		URI uri = URI.create(url);
		return rest.getForObject(uri, RssMarca.class);
	}

}
