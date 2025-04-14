package fpoly.phongndtph56750.myapplication.model;

import java.io.Serializable;
import java.util.List;

public class Voucher implements Serializable {
    private String id;
    private String nameVoucher;
    private List<DateFirebase> date;
    private boolean active;  // Đổi từ status sang active cho rõ ràng
    private int discount;

    // Constructor mặc định
    public Voucher() {
        this.id = "";
        this.nameVoucher = "";
        this.date = null;
        this.active = false;  // Giá trị mặc định là không hoạt động
        this.discount = 0;
    }

    // Constructor có tham số
    public Voucher(String id, String nameVoucher, List<DateFirebase> date, boolean active, int discount) {
        this.id = id;
        this.nameVoucher = nameVoucher;
        this.date = date;
        this.active = active;
        this.discount = discount;
    }

    // Getter và Setter cho id
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    // Getter và Setter cho tên voucher
    public String getNameVoucher() { return nameVoucher; }
    public void setNameVoucher(String nameVoucher) { this.nameVoucher = nameVoucher; }

    // Getter và Setter cho danh sách ngày (date)
    public List<DateFirebase> getDate() { return date; }
    public void setDate(List<DateFirebase> date) { this.date = date; }

    // Getter và Setter cho trạng thái active
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // Getter và Setter cho mức giảm giá
    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }

    // Phương thức toString để hiển thị voucher
    @Override
    public String toString() {
        return nameVoucher + " - Giảm " + discount + " VNĐ";
    }

    public boolean isStatus() {
        return false;
    }
}
