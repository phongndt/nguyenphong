package fpoly.phongndtph56750.myapplication.model;

public class BookingHistory {
    private long id;
    private long movieId;
    private String name;
    private String date;
    private String room;
    private String time;
    private String count;
    private String seats;
    private String foods;
    private String payment;
    private int total;
    private String user;
    private boolean used;

    // Thêm trường voucher
    private String voucherCode; // Mã voucher
    private int voucherDiscount; // Mức giảm giá voucher

    // Constructor mặc định không tham số
    public BookingHistory() {
        // Khởi tạo các giá trị mặc định cho các thuộc tính
        this.id = 0;
        this.movieId = 0;
        this.name = "";
        this.date = "";
        this.room = "";
        this.time = "";
        this.count = "";
        this.seats = "";
        this.foods = "";
        this.payment = "";
        this.total = 0;
        this.user = "";
        this.used = false;
        this.voucherCode = "";
        this.voucherDiscount = 0;
    }

    // Constructor có tham số
    public BookingHistory(long id, long movieId, String name, String date, String room, String time,
                          String count, String seats, String foods, String payment,
                          int total, String user, boolean used) {
        this.id = id;
        this.movieId = movieId;
        this.name = name;
        this.date = date;
        this.room = room;
        this.time = time;
        this.count = count;
        this.seats = seats;
        this.foods = foods;
        this.payment = payment;
        this.total = total;
        this.user = user;
        this.used = used;
        this.voucherCode = voucherCode;
        this.voucherDiscount = voucherDiscount;
    }

    // Getter và Setter cho các trường
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getFoods() {
        return foods;
    }

    public void setFoods(String foods) {
        this.foods = foods;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    // Getter and Setter cho voucherCode và voucherDiscount
    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public int getVoucherDiscount() {
        return voucherDiscount;
    }

    public void setVoucherDiscount(int voucherDiscount) {
        this.voucherDiscount = voucherDiscount;
    }
}
