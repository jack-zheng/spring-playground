package bean.t19;

public class Display {
    private String brand;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Display{" +
                "brand='" + brand + '\'' +
                '}';
    }
}
