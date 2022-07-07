package bean.t18;

import org.springframework.beans.factory.FactoryBean;

public class T18FactoryBean implements FactoryBean<T18Bean> {
    @Override
    public T18Bean getObject() throws Exception {
        return new T18Bean();
    }

    @Override
    public Class<?> getObjectType() {
        return T18Bean.class;
    }
}
