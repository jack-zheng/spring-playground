package bean.t14;

public class Cater {
    private Cat cat;

    public Cater(Cat cat) {
        this.cat = cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Cater{" +
                "cat=" + cat +
                '}';
    }
}
