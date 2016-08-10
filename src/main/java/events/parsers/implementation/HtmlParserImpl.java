package events.parsers.implementation;

import events.exception.HtmlParseException;
import events.models.CulturalEvent;
import events.models.EventSitesEnum;
import events.parsers.HtmlParser;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Default {@link HtmlParser} implementation.
 */
@Component
public class HtmlParserImpl implements HtmlParser {

    @Override
    public List<CulturalEvent> parse() {
        List<CulturalEvent> events = new ArrayList<>();
        for (EventSitesEnum entry : EventSitesEnum.values()) {
            try {
                Document doc = Jsoup.connect(entry.getUrl()).timeout(0).get();
                Elements titles = doc.select(entry.getTitleSelector());
                Elements dates = doc.select(entry.getDateSelector());
                DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(entry.getDateFormat());
                Elements places = doc.select(entry.getPlaceSelector());
                for (int i = 0; i < titles.size(); ++i) {
                    CulturalEvent event = new CulturalEvent();
                    event.setTitle(titles.get(i).text());
                    event.setDate(DateTime.parse(dates.get(i).text(), dateTimeFormatter));
                    event.setPlace(places.get(i).text());
                    events.add(event);
                }
            } catch (IOException e) {
                throw new HtmlParseException(entry.getUrl(), e);
            }
        }
        return events;
    }
}
