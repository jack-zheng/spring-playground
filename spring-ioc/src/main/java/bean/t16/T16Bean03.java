package bean.t16;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class T16Bean03 {
    @Value("t16bean03")
    private String name;

    public T16Bean03() {
        System.out.println("t1603 consturctor");
    }

    @Override
    public String toString() {
        return "T16Bean03{" +
                "name='" + name + '\'' +
                '}';
    }

    @PostConstruct
    public void postConstruct() throws Exception {
        System.out.println("invoke T16Bean02 postConstruct method....");
    }

    @PreDestroy
    public void preDestroy() throws Exception {
        System.out.println("invoke T16Bean02 preDestroy method....");
    }

    public void destroy() throws Exception {
        System.out.println("invoke T16Bean02 destroy method....");
    }

    public void init() throws Exception {
        System.out.println("invoke T16Bean02 init method....");
    }
}
