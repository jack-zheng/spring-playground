package bean.t19;

import org.springframework.beans.factory.annotation.Required;

public class WithRequiredBean {

    private String name;

    @Required
    public void setName(String name) {
        this.name = name;
    }
}
