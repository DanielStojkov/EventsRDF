package events.parsers;

import events.exception.HtmlParseException;
import events.models.CulturalEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gcvetano on 09.08.2016.
 */
@Service
public class CoolturaMkHtmlParser implements HtmlParser {
    // finding out how jsoup works
    // this url actually does NOT contain any cultural events
    private static final String URL = "http://cooltura.mk/kulturni-nastani";

    @Override
    public List<CulturalEvent> parse() {
        List<CulturalEvent> events = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(URL).timeout(0).get();
            Elements eventsElements = doc.select(".kategorija-sredni-box > .kategorija-sredni-info > .kategorija-sredni-title");
            Element first = eventsElements.first();
            String firstTitle = first.text();
            CulturalEvent e = new CulturalEvent();
            e.setTitle(firstTitle);
            events.add(e);
        } catch (IOException e) {
            throw new HtmlParseException(URL, e);
        }
        return events;
    }
}
