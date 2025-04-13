package fpoly.phongndtph56750.myapplication.model;

import java.io.Serializable;
import java.util.List;

public class Voucher implements Serializable {
    private String id;
    private String nameVoucher;
    private List<RoomFirebase> date; // Danh sách các phòng
    private String status; // Trạng thái voucher (ví dụ: "active", "inactive")
    private int discount; // Mức giảm giá của voucher (nếu cần thiết)

    // Constructor mặc định
    public Voucher() {
        this.id = "";
        this.nameVoucher = "";
        this.date = null;
        this.status = "inactive";  // Giá trị mặc định là inactive
        this.discount = 0;         // Mức giảm giá mặc định là 0
    }

    // Constructor với tham số
    public Voucher(String id, String nameVoucher, List<RoomFirebase> date, String status, int discount) {
        this.id = id;
        this.nameVoucher = nameVoucher;
        this.date = date;
        this.status = status;
        this.discount = discount;
    }

    // Getter và Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameVoucher() {
        return nameVoucher;
    }

    public void setNameVoucher(String nameVoucher) {
        this.nameVoucher = nameVoucher;
    }

    public List<RoomFirebase> getDate() {
        return date;
    }

    public void setDate(List<RoomFirebase> date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    // ToString để hiển thị thông tin voucher dễ dàng
    @Override
    public String toString() {
        return nameVoucher + " - Giảm " + discount + " VNĐ";
    }

    // Thêm phương thức kiểm tra trạng thái voucher
    public boolean isStatus() {
        return "active".equals(status);
    }
}
