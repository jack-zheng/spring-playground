package bean.others;

import javax.annotation.PreDestroy;

public class ProtoTestBean {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("bean destroyed...");
    }

    @Override
    public String toString() {
        return "ProtoTestBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
