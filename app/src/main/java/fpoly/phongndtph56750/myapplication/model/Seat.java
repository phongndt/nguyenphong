package fpoly.phongndtph56750.myapplication.model;

import java.io.Serializable;

public class Seat implements Serializable {
    private int id;
    private String title;
    private boolean selected;

    public Seat() {
    }

    public Seat(int id, String title, boolean selected) {
        this.id = id;
        this.title = title;
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}