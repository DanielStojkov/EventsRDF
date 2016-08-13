package events.web;

import events.models.CulturalEvent;
import events.parsers.HtmlParser;
import events.service.EventStorageService;
import events.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Demo controller.
 */
@RestController
@RequestMapping("/api/demo")
public class DemoController {
    // controller just for debugging purposes for now

    @Autowired
    private HtmlParser parser;

    @Autowired
    private EventStorageService storageService;

    @RequestMapping("")
    public List<CulturalEvent> demo() {
        List<CulturalEvent> events = parser.parse();
        storageService.addEventsToFile(Constants.EVENTS_FILE, events);
        storageService.addEventsToRDF("events.ttl", events);
        return events;
    }
}
