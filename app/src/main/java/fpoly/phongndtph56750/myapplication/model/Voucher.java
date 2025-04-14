package fpoly.phongndtph56750.myapplication.model;

import java.io.Serializable;
import java.util.List;

public class Voucher implements Serializable {
    private String id;
    private String nameVoucher;
    private List<DateFirebase> date;
    private boolean active;
    private int discount;

    // Constructor mặc định
    public Voucher() {
        this.id = "";
        this.nameVoucher = "";
        this.date = null;
        this.active = false;
        this.discount = 0;
    }

    // Constructor đầy đủ
    public Voucher(String id, String nameVoucher, List<DateFirebase> date, boolean active, int discount) {
        this.id = id;
        this.nameVoucher = nameVoucher;
        this.date = date;
        this.active = active;
        this.discount = discount;
    }

    // Getter & Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNameVoucher() { return nameVoucher; }
    public void setNameVoucher(String nameVoucher) { this.nameVoucher = nameVoucher; }

    public List<DateFirebase> getDate() { return date; }
    public void setDate(List<DateFirebase> date) { this.date = date; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }

    @Override
    public String toString() {
        return nameVoucher + " - Giảm " + discount + " VNĐ";
    }
}
