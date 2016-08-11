package events.parsers.implementation;

import events.exception.HtmlParseException;
import events.models.CulturalEvent;
import events.models.EventSitesEnum;
import events.parsers.HtmlParser;
import events.util.CrawlerRunnable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Default {@link HtmlParser} implementation.
 */
@Component
public class HtmlParserImpl implements HtmlParser {

    @Override
    public List<CulturalEvent> parse() {
        final List<CulturalEvent> events = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        List<EventSitesEnum> entries = Arrays.asList(EventSitesEnum.values());
        entries.forEach(e -> {
            Thread t = new Thread(new CrawlerRunnable(events, e));
            threads.add(t);
        });
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new HtmlParseException(e);
            }
        });

        return events;
    }
}
