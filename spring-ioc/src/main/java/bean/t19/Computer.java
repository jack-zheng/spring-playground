package bean.t19;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class Computer {
    private String brand;
    @Autowired
    private Display display;

    @Resource
    private Keyboard keyboard;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    //@Resource(name="keyboard")
    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "brand='" + brand + '\'' +
                ", display=" + display +
                ", keyboard=" + keyboard +
                '}';
    }
}
