package events.service.implementation;

import com.sun.javaws.security.Resource;
import events.models.CulturalEvent;
import events.service.ModelCreationService;
import events.util.Constants;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Daniel on 8/13/2016.
 */
@Service
public class ModelCreationServiceImpl implements ModelCreationService{

    @Autowired
    private Environment environment;

    @Override
    public Model createModel(List<CulturalEvent> events) {
        Model model = ModelFactory.createDefaultModel();
        String address = environment.getProperty("server.address");
        String namespace = environment.getProperty("ontology.model.namespace");
        model.setNsPrefix(environment.getProperty("ontology.model.namespace"),
                environment.getProperty("ontology.url"));

        org.apache.jena.rdf.model.Resource placeType = model.createResource(namespace + ":" + "Place");
        org.apache.jena.rdf.model.Resource eventType = model.createResource(namespace + ":" + "Event");
        Property hasPlace = model.createProperty(namespace + ":" + "hasPlace");
        Property hasTimeStamp = model.createProperty(namespace + ":" + "hasTimeStamp");

        for(CulturalEvent event : events) {
            String eventName = event.getTitle().replaceAll("\\s+", "_").replaceAll("\"", "“");
            String placeName = event.getPlace().replaceAll("\\s+", "_").replaceAll("\"", "“");
            //TODO: Make IDs as url
            org.apache.jena.rdf.model.Resource place = model.createResource(processURI(placeName, Constants.PLACE_PATH));
            org.apache.jena.rdf.model.Resource e = model.createResource(processURI(eventName, Constants.EVENTS_PATH));
            Literal time = model.createTypedLiteral(event.getDate().toCalendar(null));


            place.addProperty(RDF.type, placeType);

            e.addProperty(RDF.type, eventType);
            e.addProperty(hasPlace, place);
            e.addProperty(hasTimeStamp, time);
        }

        return model;
    }

    private String processURI(String name, String path) {
        return "http://"
                + environment.getProperty("server.address") + ":"
                + environment.getProperty("server.port") + "/"
                + path + "/"
                + name;
    }

}
