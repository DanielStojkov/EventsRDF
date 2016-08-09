package events.models;

/**
 * A model representing a cultural event.
 */
public class CulturalEvent {
    // TODO: gcvetano 09.08.2016 make date DateTime instead of String
    private String date;
    private String title;
    private String place;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
