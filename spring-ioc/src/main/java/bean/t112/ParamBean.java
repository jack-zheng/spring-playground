package bean.t112;

import org.springframework.beans.factory.annotation.Value;

public class ParamBean {
    @Value("param")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ParamBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
