package bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Student {
    // 基础类型
    private String name;
    private int age;
    private String[] alias;
    // bean 类型
    private Address address;
    // 内部类
    private Phone phone;
    // collection
    private List<String> hardware;
    private Set<String> sports;
    private Map<String, String> cards;
    private Properties infos;
    // empty + null
    private String gender;
    private String friends;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public void setHardware(List<String> hardware) {
        this.hardware = hardware;
    }

    public void setSports(Set<String> sports) {
        this.sports = sports;
    }

    public void setCards(Map<String, String> cards) {
        this.cards = cards;
    }

    public void setInfos(Properties infos) {
        this.infos = infos;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", alias=" + Arrays.toString(alias) +
                ", address=" + address +
                ", phone=" + phone +
                ", hardware=" + hardware +
                ", sports=" + sports +
                ", cards=" + cards +
                ", infos=" + infos +
                ", gender='" + gender + '\'' +
                ", friends='" + friends + '\'' +
                '}';
    }

    static class Phone{
        private String brand;
        private float price;

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Phone{" +
                    "brand='" + brand + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
}
