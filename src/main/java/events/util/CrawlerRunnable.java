package events.util;

import events.exception.HtmlParseException;
import events.models.CulturalEvent;
import events.models.EventSitesEnum;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Runnable interface for concurrent crawling of html.
 */
public class CrawlerRunnable implements Runnable {
    private final List<CulturalEvent> events;
    private EventSitesEnum entry;

    public CrawlerRunnable(List<CulturalEvent> events, EventSitesEnum entry) {
        this.events = events;
        this.entry = entry;
    }

    @Override
    public void run() {
        try {
            List<CulturalEvent> localEvents = new ArrayList<>();
            Document doc = Jsoup.connect(entry.getUrl()).timeout(0).get();
            Elements titles = doc.select(entry.getTitleSelector());
            Elements dates = doc.select(entry.getDateSelector());
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(entry.getDateFormat());
            Elements places = doc.select(entry.getPlaceSelector());
            Elements eventUrls = doc.select(entry.getEventUrlSelector());
            Elements imgUrls = doc.select(entry.getImgHolderSelector());
            for (int i = 0; i < titles.size(); ++i) {
                CulturalEvent event = new CulturalEvent();
                event.setTitle(titles.get(i).text());
                String date = dates.get(i).text();
                date = entry.getDateConverter().apply(date);
                event.setDate(DateTime.parse(date, dateTimeFormatter));
                event.setPlace(places.get(i).text());
                event.setUrl(entry.getEventUrlDomain() + eventUrls.get(i).attr(Constants.ATTRIBUTE_HREF));
                event.setImgUrl(entry.getImgUrlExtractor().apply(imgUrls.get(i)));
                localEvents.add(event);
            }
            synchronized (events) {
                events.addAll(localEvents);
            }
        } catch (Exception e) {
            throw new HtmlParseException(e);
        }
    }
}
