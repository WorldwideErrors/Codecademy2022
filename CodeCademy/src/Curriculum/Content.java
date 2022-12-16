package Curriculum;

import People.Cursist;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

abstract class Content {

    int itemID;
    String title;
    String description;
    Date publishedDate;
    Map<Cursist, Integer> cursists;

    public Content(int itemID, String title, String description, Date publishedDate) {
        this.itemID = itemID;
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.cursists = new HashMap<>();
    }

    public Content(int itemID, String title) {
        this.itemID = itemID;
        this.title = title;
        this.cursists = new HashMap<>();
    }

    public void addCursists(Cursist cursist) {
        cursists.put(cursist, 0);
    }

    public void addProgress(Cursist cursist, int amount) {
        cursists.put(cursist, cursists.get(cursist) + amount);
    }

    public int getProgress(Cursist cursist) {
        return cursists.get(cursist);
    }
}
