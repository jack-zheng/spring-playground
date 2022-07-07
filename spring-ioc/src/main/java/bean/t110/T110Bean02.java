package bean.t110;

public class T110Bean02 {
    private String name;

    public T110Bean02(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "T110Bean02{" +
                "name='" + name + '\'' +
                '}' + ", hash: " + hashCode();
    }
}
