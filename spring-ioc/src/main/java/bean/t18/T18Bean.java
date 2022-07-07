package bean.t18;

import org.springframework.beans.factory.annotation.Value;

public class T18Bean {
    @Value("t18")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "T18Bean{" +
                "name='" + name + '\'' +
                '}';
    }
}
