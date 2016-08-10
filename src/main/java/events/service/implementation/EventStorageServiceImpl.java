package events.service.implementation;

import events.exception.StorageException;
import events.models.CulturalEvent;
import events.service.EventStorageService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Default {@link EventStorageService} implementation.
 */
@Service
public class EventStorageServiceImpl implements EventStorageService {

    @Override
    public void addEventsToFile(String fileName, List<CulturalEvent> events) {
        try {
            StringBuilder sb = new StringBuilder();
            for (CulturalEvent event : events) {
                sb.append(event.toString());
                String newline = System.getProperty(System.getProperty("line.separator"));
                sb.append(System.getProperty("line.separator"));
            }
            // FIXME: 10.08.2016 it saves to the file, but in the target directory (it should be in resources folder)
            Resource resource = new ClassPathResource(fileName);
            Files.write(Paths.get(resource.getURI()), sb.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new StorageException(String.format("Error while reading %s", fileName), e);
        }
    }
}
