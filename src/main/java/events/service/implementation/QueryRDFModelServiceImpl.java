package events.service.implementation;

import events.models.CulturalEvent;
import events.service.QueryRDFModelService;
import org.apache.jena.datatypes.xsd.XSDDateTime;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RSS;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Daniel on 8/18/2016.
 */
@Service
public class QueryRDFModelServiceImpl implements QueryRDFModelService {

    @Autowired
    private Environment environment;

    @Override
    public List<CulturalEvent> listAll() {
        List<CulturalEvent> result = new LinkedList<>();
        Model model = readModel();

        String namespace = environment.getProperty("ontology.model.namespace");
        Resource eventType = model.createResource(namespace + ":" + "Event");


        StmtIterator iterator = model.listStatements(new SimpleSelector(null, RDF.type, eventType));
        while(iterator.hasNext()) {
            Statement statement = iterator.nextStatement();
            Resource event = statement.getSubject();
            result.add(getCulturalEventFromModel(event, model));
        }
        return result;
    }

    @Override
    public CulturalEvent getByURI(String uri) {
        Model model = readModel();
        return getCulturalEventFromModel(model.getResource(uri), model);
    }

    @Override
    public CulturalEvent getByURIPathAndName(String path, String name) {
        return getByURI("http://"
                + environment.getProperty("server.address") + ":"
                + environment.getProperty("server.port") + "/"
                + path + "/"
                + name);
    }


    @Override
    public List<CulturalEvent> sortByDate(boolean asc) {
        return null;
    }

    private CulturalEvent getCulturalEventFromModel(Resource event, Model model) {
        String namespace = environment.getProperty("ontology.model.namespace");
        Property hasPlace = model.createProperty(namespace + ":" + "hasPlace");
        Property hasTimeStamp = model.createProperty(namespace + ":" + "hasTimeStamp");

        String uri = event.getURI();
        String title = event.getProperty(RSS.title).getObject().asLiteral().getString();
        String URL =  event.getProperty(RSS.url).getObject().asLiteral().getString();
        String image = event.getProperty(RSS.image).getObject().asLiteral().getString();
        String place = event.getProperty(hasPlace).getObject().asResource()
                .getProperty(RSS.name).getObject().asLiteral().getString();
        Calendar timeStamp = ((XSDDateTime) event.getProperty(hasTimeStamp).getObject().asLiteral().getValue())
                .asCalendar();

        CulturalEvent culturalEvent = new CulturalEvent();
        culturalEvent.setURI(uri);
        culturalEvent.setTitle(title);
        culturalEvent.setUrl(URL);
        culturalEvent.setImgUrl(image);
        culturalEvent.setPlace(place);
        culturalEvent.setDate(new DateTime(timeStamp.getTimeInMillis(),
                DateTimeZone.forID(timeStamp.getTimeZone().getID())));
        return culturalEvent;
    }

    private Model readModel() {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // use the FileManager to find the input file
        InputStream in = FileManager.get().open(environment.getProperty("ontology.model.name"));
        if (in == null) {
            throw new IllegalArgumentException(
                    "File not found");
        }


        model.read(in, null, "TTL");

        return model;
    }
}
