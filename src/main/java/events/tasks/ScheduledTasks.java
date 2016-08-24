package events.tasks;

import events.models.CulturalEvent;
import events.parsers.HtmlParser;
import events.service.EventStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A component that runs on a schedule.
 */
@Component
public class ScheduledTasks {

    @Autowired
    private Environment environment;

    @Autowired
    private HtmlParser parser;

    @Autowired
    private EventStorageService storageService;

    @Scheduled(cron = "0 0 * * * *")
    public void example() {
        System.out.println("Crawler has been started");
        List<CulturalEvent> events = parser.parse();
        storageService.addEventsToRDF(environment.getProperty("ontology.model.name"), events);
    }
}
