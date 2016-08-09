package events.parsers;

import events.models.CulturalEvent;

import java.util.List;

/**
 * Html parser interface.
 */
public interface HtmlParser {

    List<CulturalEvent> parse();
}
