package events.parsers.implementation;

import events.exception.HtmlParseException;
import events.models.CulturalEvent;
import events.models.EventSitesEnum;
import events.parsers.HtmlParser;
import events.util.MacedonianMonthsConverter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Default {@link HtmlParser} implementation.
 */
@Component
public class HtmlParserImpl implements HtmlParser {

    @Override
    public List<CulturalEvent> parse() {
        List<CulturalEvent> events = new ArrayList<>();
        for (EventSitesEnum entry : EventSitesEnum.values()) {
            try {
                Document doc = Jsoup.connect(entry.getUrl()).timeout(0).get();
                Elements titles = doc.select(entry.getTitleSelector());
                Elements dates = doc.select(entry.getDateSelector());
                DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(entry.getDateFormat());
                Elements places = doc.select(entry.getPlaceSelector());
                for (int i = 0; i < titles.size(); ++i) {
                    CulturalEvent event = new CulturalEvent();
                    event.setTitle(titles.get(i).text());
                    String date = dates.get(i).text();

                    // specific date formats from sources
                    if (entry.getUrl().equals(EventSitesEnum.TIME_MK.getUrl())) {
                        //Wednesday, Aug 10 at 22:00h - 02:00h
                        //parse the date manually and then use a date time formatter
                        String atString = " at ";
                        int atIndex = date.indexOf(atString);
                        String dayMonthDay = date.substring(0, atIndex); // gets the 'Wednesday, Aug 10' part
                        String hours = date.substring(atIndex + atString.length(), atIndex + atString.length() + 2);
                        String year = DateTime.now().year().getAsString(); // possible bug for New Years Eve
                        date = dayMonthDay + " " + hours + " " + year;
                    } else if (entry.getUrl().equals(EventSitesEnum.EVENTI_MK.getUrl())) {
                        String atSign = " @ ";
                        String startDate = date.substring(0, date.indexOf(atSign) + atSign.length() + 2);
                        String [] dateParts = startDate.split(" ");
                        String month = MacedonianMonthsConverter.getEnglishNameForMonth(dateParts[0]);
                        String day = dateParts[1];
                        String hour = dateParts[3];
                        String year = DateTime.now().year().getAsString();
                        date = year + " " + month + " " + day + " " + hour;
                    }

                    event.setDate(DateTime.parse(date, dateTimeFormatter));
                    event.setPlace(places.get(i).text());
                    events.add(event);
                }
            } catch (IOException e) {
                throw new HtmlParseException(entry.getUrl(), e);
            }
        }
        return events;
    }
}
