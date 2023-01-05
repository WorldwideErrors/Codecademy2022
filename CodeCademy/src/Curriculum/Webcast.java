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

    public Boolean formatURL(String url) {
        // Split URL
        String[] parts = url.split("\\.");

        // check if URL has 3 parts
        if (parts.length != 3) {
            return false;
        }

        // check if starts with http / https
        if (!parts[0].equals("http://") && !parts[0].equals("https://")) {
            return false;
        }
        // check if minimum of 1 letter
        for (int j = 0; j < parts.length; j++) {
            if (parts[j].length() < 1) {
                return false;
            }
        }
        return true;

    }

}
