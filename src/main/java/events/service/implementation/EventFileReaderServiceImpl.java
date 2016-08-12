package events.service.implementation;

import events.exception.ReaderException;
import events.models.CulturalEvent;
import events.service.EventFileReaderService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of {@link events.service.EventFileReaderService}.
 */
@Service
public class EventFileReaderServiceImpl implements EventFileReaderService {

    @Override
    public List<CulturalEvent> readEventsFromFile(String filename) {
        List<CulturalEvent> events = new ArrayList<>();
        try {
            Resource resource = new ClassPathResource(filename);
            List<String> eventsAsStrings = Files.readAllLines(Paths.get(resource.getURI()));
            eventsAsStrings.forEach(e -> events.add(CulturalEvent.valueOf(e)));
        } catch (IOException e) {
            throw new ReaderException(e);
        }
        return events;
    }
}
