package events.tasks;

import events.models.CulturalEvent;
import events.parsers.HtmlParser;
import events.service.EventStorageService;
import events.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * A component that runs on a schedule.
 */
@Component
public class ScheduledTasks {

    @Autowired
    private HtmlParser parser;

    @Autowired
    private EventStorageService storageService;

    @Scheduled(cron = "0 * * * * *")
    public void example() {
        List<CulturalEvent> events = parser.parse();
        storageService.addEventsToFile(Constants.EVENTS_FILE, events);
    }
}
