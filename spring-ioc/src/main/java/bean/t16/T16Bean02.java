package bean.t16;

import org.springframework.beans.factory.annotation.Value;

public class T16Bean02{
    @Value("t16bean02")
    private String name;

    public T16Bean02() {
    }

    @Override
    public String toString() {
        return "T16Bean{" +
                "name='" + name + '\'' +
                '}';
    }

    public void destroy() throws Exception {
        System.out.println("invoke T16Bean02 destroy method....");
    }

    public void init() throws Exception {
        System.out.println("invoke T16Bean02 init method....");
    }
}
