package bean.t14;

public class T14BeanA {
    private String name;
    private T14BeanB t14BeanB;

    public void setT14BeanB(T14BeanB t14BeanB) {
        this.t14BeanB = t14BeanB;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "T14BeanA{" +
                "name='" + name + '\'' +
                ", t14BeanB=" + t14BeanB +
                '}';
    }
}
