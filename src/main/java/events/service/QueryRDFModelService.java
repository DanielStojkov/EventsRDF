package events.service;

import events.models.CulturalEvent;

import java.util.List;

/**
 * Created by Daniel on 8/18/2016.
 */
public interface QueryRDFModelService {

    public List<CulturalEvent> listAll();

    public CulturalEvent getByURI(String uri);

    public CulturalEvent getByURIPathAndName(String path, String name);

    public List<CulturalEvent> sortByDate(boolean asc);
}
