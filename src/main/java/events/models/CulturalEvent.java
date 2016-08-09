package events.models;

import org.joda.time.DateTime;

/**
 * Created by Daniel on 8/4/2016.
 */
public class CulturalEvent {
    private DateTime date;
    private String title;
    private String place;

    public DateTime getDate() {
        return date;
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
}
