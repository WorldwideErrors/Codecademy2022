package Curriculum;

import java.util.Date;

public class Webcast extends Content {

    private String speakerName;
    private String organisation;
    private int duration;
    private String url;

    public Webcast(String speakerName, String organisation, int duration, String url, int itemID, String title, String description, Date publishedDate) {
        super(itemID, title, description, publishedDate);
        this.speakerName = speakerName;
        this.organisation = organisation;
        this.duration = duration;
        this.url = url;
    }

    public Webcast(String speakerName, String organisation, int duration, String url, int itemID, String title) {
        super(itemID, title);
        this.speakerName = speakerName;
        this.organisation = organisation;
        this.duration = duration;
        this.url = url;
    }
    
    public String formatURL(String url) {
        return "";
    }

}
