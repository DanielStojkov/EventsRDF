package events.service;

import events.models.CulturalEvent;

import java.util.List;

/**
 * Service for reading events from file storage.
 */
public interface EventFileReaderService {
    List<CulturalEvent> readEventsFromFile(String filename);
}
