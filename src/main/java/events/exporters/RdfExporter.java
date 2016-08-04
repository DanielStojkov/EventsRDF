package events.exporters;

import events.models.CulturalEvent;

/**
 * Created by Daniel on 8/4/2016.
 */
public interface RdfExporter {

    public void export(CulturalEvent event);
}
