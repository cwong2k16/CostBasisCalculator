package object;

public class RecordData {
    private String name;
    private double size;
    private double price;
    private double amount;

    public RecordData(String name, double size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.amount = size*price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
