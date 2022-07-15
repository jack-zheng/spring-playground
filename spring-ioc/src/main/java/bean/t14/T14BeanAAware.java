package bean.t14;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class T14BeanAAware implements ApplicationContextAware {
    private String name;
    private T14BeanB t14BeanB;
    private ApplicationContext applicationContext;

    public void setName(String name) {
        this.name = name;
    }

    public T14BeanB getT14BeanB() {
        return applicationContext.getBean("t14BeanB", T14BeanB.class);
    }

    @Override
    public String toString() {
        return "T14BeanA{" +
                "name='" + name + '\'' +
                ", t14BeanB=" + t14BeanB +
                '}';
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
