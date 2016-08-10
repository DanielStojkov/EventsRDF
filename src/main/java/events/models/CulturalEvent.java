package events.models;

import org.joda.time.DateTime;

/**
 * A model representing a cultural event.
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

    @Override
    public String toString() {
        return String.format("%s,%s,%s", getDate(), getTitle(), getPlace());
    }
}
