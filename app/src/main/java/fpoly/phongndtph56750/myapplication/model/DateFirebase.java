package fpoly.phongndtph56750.myapplication.model;

import java.io.Serializable;

public class DateFirebase implements Serializable {
    private String title;

    public DateFirebase() {
    }

    public DateFirebase(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
