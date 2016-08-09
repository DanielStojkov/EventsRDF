package events.parsers;

import events.models.CulturalEvent;

import java.util.List;

/**
 * Created by Daniel on 8/4/2016.
 */
public interface HtmlParser {

    List<CulturalEvent> parse();
}
