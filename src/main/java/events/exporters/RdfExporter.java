package events.exporters;

import events.models.CulturalEvent;

/**
 * Rdf exporter.
 */
public interface RdfExporter {

    void export(CulturalEvent event);
}
