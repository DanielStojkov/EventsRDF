package events.service;

import events.models.CulturalEvent;
import org.apache.jena.rdf.model.Model;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Daniel on 8/13/2016.
 */
public interface ModelCreationService {

    public Model createModel(List<CulturalEvent> events) throws UnsupportedEncodingException;

}
