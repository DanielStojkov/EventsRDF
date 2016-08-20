package events.service.implementation;

import events.models.CulturalEvent;
import events.service.ModelCreationService;
import events.util.Constants;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Daniel on 8/13/2016.
 */
@Service
public class ModelCreationServiceImpl implements ModelCreationService{

    @Autowired
    private Environment environment;

    @Override
    public Model createModel(List<CulturalEvent> events) throws UnsupportedEncodingException {
        Model model = ModelFactory.createDefaultModel();
        String namespace = environment.getProperty("ontology.model.namespace");
        model.setNsPrefix(environment.getProperty("ontology.model.namespace"),
                environment.getProperty("ontology.url"));

        Resource placeType = model.createResource(namespace + ":" + "Place");
        Resource eventType = model.createResource(namespace + ":" + "Event");
        Property hasPlace = model.createProperty(namespace + ":" + "hasPlace");
        Property hasTimeStamp = model.createProperty(namespace + ":" + "hasTimeStamp");

        for(CulturalEvent event : events) {
            String eventName = event.getTitle().replaceAll("\\s+", "_").replaceAll("\"", "“");
            String placeName = event.getPlace().replaceAll("\\s+", "_").replaceAll("\"", "“");

            Resource place = model.createResource(processURI(placeName, Constants.PLACE_PATH));
            Resource e = model.createResource(processURI(eventName, Constants.EVENTS_PATH));
            Literal time = model.createTypedLiteral(event.getDate().toCalendar(null));

            place.addProperty(RDF.type, placeType);
            place.addProperty(RSS.name, event.getPlace());

            e.addProperty(RDF.type, eventType);
            e.addProperty(RSS.title, event.getTitle());
            e.addProperty(RSS.url, event.getUrl());
            e.addProperty(RSS.image, event.getImgUrl());
            e.addProperty(hasPlace, place);
            e.addProperty(hasTimeStamp, time);
        }

        return model;
    }


    private String processURI(String name, String path) throws UnsupportedEncodingException {
        return "http://"
                + environment.getProperty("server.address") + ":"
                + environment.getProperty("server.port") + "/"
                + path + "/"
                + name;
    }

}
