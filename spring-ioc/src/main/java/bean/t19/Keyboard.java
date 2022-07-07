package bean.t19;

import javax.annotation.PostConstruct;

public class Keyboard {
    private String brand;

    @PostConstruct
    public void postConstruct() {
        System.out.println("call post construct method...");
    }

    public Keyboard() {
        System.out.println("call construct...");
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "brand='" + brand + '\'' +
                '}';
    }
}
