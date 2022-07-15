package bean.t13;

public class StaticFactoryMethod {
    public static T13Bean getT13Bean() {
        T13Bean t13Bean = new T13Bean();
        t13Bean.setName("t13_static_method");
        return t13Bean;
    }
}
