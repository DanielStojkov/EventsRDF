package events.service;

import events.models.CulturalEvent;

import java.util.List;

/**
 * Created by Daniel on 8/18/2016.
 */
public interface QueryRDFModelService {

    public List<CulturalEvent> listAll();

    public CulturalEvent getById(String uri);

    public List<CulturalEvent> sortByDate(boolean asc);
}
