package bean.t110;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class T110Bean {
    private String name;

    @Value("t110Name")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "T110Bean{" +
                "name='" + name + '\'' +
                '}';
    }
}
