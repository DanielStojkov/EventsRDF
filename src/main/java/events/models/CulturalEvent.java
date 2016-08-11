package events.models;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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

    /**
     * Returns the day in the format yyyy/MM/dd HH.
     * @return String representation of the date.
     */
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm");
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

    @Override
    public String toString() {
        return String.format("%s::%s::%s", getTitle(), getFormattedDate(), getPlace());
    }
}
