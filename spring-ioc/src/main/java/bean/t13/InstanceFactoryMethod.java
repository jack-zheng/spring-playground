package bean.t13;

public class InstanceFactoryMethod {
    public T13Bean getT13Bean() {
        T13Bean t13Bean = new T13Bean();
        t13Bean.setName("t13_instance");
        return t13Bean;
    }
}
