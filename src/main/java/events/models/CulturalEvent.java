package events.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * A model representing a cultural event.
 */
public class CulturalEvent {
    private static final String propertiesSplitter = "::";
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm");

    private String URI;

    private DateTime date;
    private String title;
    private String place;
    private String url;
    private String imgUrl;

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    @JsonIgnore
    public DateTime getDate() {
        return date;
    }

    /**
     * Returns the day in the format yyyy/MM/dd HH:mm.
     * @return String representation of the date.
     */
    public String getFormattedDate() {
        return date.toString(formatter);
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s%s%s%s%s%s%s",
                getTitle(), propertiesSplitter,
                getFormattedDate(), propertiesSplitter,
                getPlace(), propertiesSplitter,
                getUrl(), propertiesSplitter,
                getImgUrl());
    }

    public static CulturalEvent valueOf(String eventString) {
        String[] properties = eventString.split(propertiesSplitter);
        String title = properties[0];
        DateTime date = DateTime.parse(properties[1], formatter);
        String place = properties[2];
        String url = properties[3];
        String imgUrl = properties[4];

        CulturalEvent event = new CulturalEvent();
        event.setTitle(title);
        event.setDate(date);
        event.setPlace(place);
        event.setUrl(url);
        event.setImgUrl(imgUrl);
        return event;
    }
}
