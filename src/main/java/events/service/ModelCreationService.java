package events.service;

import events.models.CulturalEvent;
import org.apache.jena.rdf.model.Model;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Service for creating a rdf model.
 */
public interface ModelCreationService {

    Model createModel(List<CulturalEvent> events) throws UnsupportedEncodingException;

}
