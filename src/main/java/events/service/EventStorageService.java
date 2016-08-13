package events.service;

import events.models.CulturalEvent;

import java.io.IOException;
import java.util.List;

/**
 * Service for storing the {@link CulturalEvent} list to a file.
 */
public interface EventStorageService {
    /**
     * Stores the events to a file.
     * @param fileName the file to which the events will be stored.
     * @param events the list of events.
     */
    void addEventsToFile(String fileName, List<CulturalEvent> events);

    void addEventsToRDF(String fileName, List<CulturalEvent> events);
}
