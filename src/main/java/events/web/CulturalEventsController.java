package events.web;

import events.models.CulturalEvent;
import events.service.EventFileReaderService;
import events.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for returning mock data for events.
 */
@RestController
@RequestMapping("api/events")
public class CulturalEventsController {

    @Autowired
    private EventFileReaderService readerService;


    @RequestMapping("/mock")
    public List<CulturalEvent> getMockData() {
        return readerService.readEventsFromFile(Constants.EVENTS_FILE);
    }
}
