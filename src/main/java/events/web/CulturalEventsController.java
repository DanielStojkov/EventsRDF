package events.web;

import events.models.CulturalEvent;
import events.service.EventFileReaderService;
import events.service.QueryRDFModelService;
import events.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller for returning mock data for events.
 */
@RestController
@RequestMapping(Constants.EVENTS_PATH)
public class CulturalEventsController {

    @Autowired
    private EventFileReaderService readerService;

    @Autowired
    private QueryRDFModelService queryRDFModelService;

    @RequestMapping("/mock")
    public List<CulturalEvent> getMockData() {
        return readerService.readEventsFromFile(Constants.EVENTS_FILE);
    }

    @RequestMapping()
    public List<CulturalEvent> getAll() {
        return queryRDFModelService.listAll();
    }

    @RequestMapping("/{event:.+}")
    public CulturalEvent getEvent(@PathVariable String event, HttpServletRequest request) {
        return queryRDFModelService.getByURIPathAndName(Constants.EVENTS_PATH, event);
    }
}
