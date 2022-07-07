package bean.t16;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class T16Bean implements InitializingBean, DisposableBean {
    @Value("t16bean")
    private String name;

    public T16Bean() {
    }

    @Override
    public String toString() {
        return "T16Bean{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("invoke destroy method....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("invoke afterPropertiesSet method....");
    }
}
