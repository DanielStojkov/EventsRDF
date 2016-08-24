package events.service;

import events.models.CulturalEvent;

import java.util.List;

/**
 * Service for querying the rdf model.
 */
public interface QueryRDFModelService {

    List<CulturalEvent> listAll();

    CulturalEvent getByURI(String uri);

    CulturalEvent getByURIPathAndName(String path, String name);

    List<CulturalEvent> sortByDate(boolean asc);
}
