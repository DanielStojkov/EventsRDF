package events.models;

/**
 * Enumeration for the sources that provide Cultural Events.
 */
public enum EventSitesEnum {

    COOLTURA("http://cooltura.mk/kulturni-nastani",
        ".kategorija-sredni-box > .kategorija-sredni-info > .kategorija-sredni-title",
        ".kategorija-sredni-box > .kategorija-sredni-info > .kategorija-sredni-data",
        "MM/dd/yyyy - HH:mm",
        ".kategorija-sredni-box > .kategorija-sredni-info > .kategorija-sredni-data");
    // no place provided for cooltura.mk
    // cooltura.mk is actually a site for news related to cultural stuff
    // move along, nothing to see here (https://www.youtube.com/watch?v=5NNOrp_83RU)

    // TODO: gcvetano 09.08.2016 find some sites that provide cultural events

    private String url;
    private String titleSelector;
    private String dateSelector;
    private String dateFormat;
    private String placeSelector;

    EventSitesEnum(String url, String titleSelector, String dateSelector, String dateFormat, String placeSelector) {
        this.url = url;
        this.titleSelector = titleSelector;
        this.dateSelector = dateSelector;
        this.dateFormat = dateFormat;
        this.placeSelector = placeSelector;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitleSelector() {
        return titleSelector;
    }

    public void setTitleSelector(String titleSelector) {
        this.titleSelector = titleSelector;
    }

    public String getDateSelector() {
        return dateSelector;
    }

    public void setDateSelector(String dateSelector) {
        this.dateSelector = dateSelector;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getPlaceSelector() {
        return placeSelector;
    }

    public void setPlaceSelector(String placeSelector) {
        this.placeSelector = placeSelector;
    }
}
