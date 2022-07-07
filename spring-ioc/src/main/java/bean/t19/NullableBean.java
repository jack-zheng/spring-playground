package bean.t19;

public class NullableBean {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NullableBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
