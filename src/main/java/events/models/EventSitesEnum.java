package events.models;

import events.util.MacedonianMonthsConverter;
import org.joda.time.DateTime;

import java.util.function.Function;

/**
 * Enumeration for the sources that provide Cultural Events.
 */
public enum EventSitesEnum {

    ZABAVI_MK("http://zabavi.mk/events.php",
            ".items-row > .span12 > .item > .item_header > h1 > a",
            ".items-row > .span12 > .item > .item_header > .item_published",
            date -> {
                //Вторник, 09 Август 21:00
                String separator = ", ";
                String withoutDay = date.substring(date.indexOf(separator) + separator.length());
                String[] dateParts = withoutDay.split(" ");
                String year = DateTime.now().year().getAsString();
                return year + " " + dateParts[0] + " "
                        + MacedonianMonthsConverter.getEnglishNameForMonth(dateParts[1]) + " " + dateParts[2];
            },
            "yyyy dd MMM HH:mm",
            ".items-row > .span12 > .item > .item_header > span > a"),

    EVENTI_MK("http://eventi.mk/skopje/nastani/kategorija/kultura/",
            ".type-tribe_events > .entry-title",
            ".type-tribe_events > .tribe-events-event-meta > .location > .time-details",
            date -> {
                //август 5 @ 20:00 - август 19 @ 20:00
                String atSign = " @ ";
                String startDate = date.substring(0, date.indexOf(atSign) + atSign.length() + 5);
                String [] dateParts = startDate.split(" ");
                String month = MacedonianMonthsConverter.getEnglishNameForMonth(dateParts[0]);
                String day = dateParts[1];
                String hour = dateParts[3];
                String year = DateTime.now().year().getAsString();
                return year + " " + month + " " + day + " " + hour;
            },
            "yyyy MMM dd HH:mm",
            ".type-tribe_events > .tribe-events-event-meta > .location > .tribe-events-venue-details"),

    TIME_MK("http://www.time.mk/nastani/macedonia/3",
            ".event_single > .event_actions > p",
            ".event_single > .event_actions > #event_date",
            date -> {
                //Sunday, Aug 14 at 21:00h
                String atString = " at ";
                int atIndex = date.indexOf(atString);
                String dayMonthDay = date.substring(0, atIndex); // gets the 'Wednesday, Aug 10' part
                String hours = date.substring(atIndex + atString.length(), atIndex + atString.length() + 5);
                String year = DateTime.now().year().getAsString(); // possible bug for New Years Eve
                return dayMonthDay + " " + hours + " " + year;
            },
            "EEEE, MMM dd HH:mm yyyy",
            ".event_single > .event_actions > #event_location > a");


    // TODO: gcvetano 09.08.2016 find some sites that provide cultural events

    private String url;
    private String titleSelector;
    private String dateSelector;
    private String dateFormat;
    private Function<String, String> dateConverter;
    private String placeSelector;

    EventSitesEnum(String url, String titleSelector, String dateSelector, Function<String, String> dateConverter,
                   String dateFormat, String placeSelector) {
        this.url = url;
        this.titleSelector = titleSelector;
        this.dateSelector = dateSelector;
        this.dateFormat = dateFormat;
        this.dateConverter = dateConverter;
        this.placeSelector = placeSelector;
    }

    public String getUrl() {
        return url;
    }

    public String getTitleSelector() {
        return titleSelector;
    }

    public String getDateSelector() {
        return dateSelector;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public Function<String, String> getDateConverter() {
        return dateConverter;
    }

    public String getPlaceSelector() {
        return placeSelector;
    }
}
