package bean.t14;

import org.springframework.beans.factory.annotation.Lookup;

public abstract class T14BeanAMethodInjection {
    private String name;
    private T14BeanB t14BeanB;

    public void setName(String name) {
        this.name = name;
    }

    @Lookup
    public abstract T14BeanB getT14BeanB();

    @Override
    public String toString() {
        return "T14BeanA{" +
                "name='" + name + '\'' +
                ", t14BeanB=" + t14BeanB +
                '}';
    }
}
