package events.service.implementation;

import events.exception.StorageException;
import events.models.CulturalEvent;
import events.service.EventStorageService;
import events.service.ModelCreationService;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Default {@link EventStorageService} implementation.
 */
@Service
public class EventStorageServiceImpl implements EventStorageService {

    @Autowired
    private ModelCreationService modelCreationService;

    @Override
    public void addEventsToFile(String fileName, List<CulturalEvent> events) {
        try {
            StringBuilder sb = new StringBuilder();
            for (CulturalEvent event : events) {
                sb.append(event.toString());
                sb.append(System.getProperty("line.separator"));
            }
            // FIXME: 10.08.2016 it saves to the file, but in the target directory (it should be in resources folder)
            Resource resource = new ClassPathResource(fileName);
            Files.write(Paths.get(resource.getURI()), sb.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new StorageException(String.format("Error while reading %s", fileName), e);
        }
    }

    @Override
    public void addEventsToRDF(String fileName, List<CulturalEvent> events) {
        try {
            Model newModel = modelCreationService.createModel(events);

            Resource resource = new ClassPathResource(fileName);
            EncodedResource encodedResource = new EncodedResource(resource, "UTF8");
            if(resource.exists()) {
                Model model = ModelFactory.createDefaultModel();
                model.read(encodedResource.getInputStream(), "", "TTL");
                newModel.union(model);
            }
            OutputStream outputStream = new FileOutputStream(resource.getFile());
            newModel.write(outputStream, "TTL");
        } catch (IOException e) {
            throw  new StorageException(String.format("Error on read/write file %s", fileName), e);
        }

    }
}
