package fpoly.phongndtph56750.myapplication.model;

public class PaymentMethod {

    private int type;
    private String name;

    public PaymentMethod() {}

    public PaymentMethod(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
