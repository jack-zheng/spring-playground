package bean.t110;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FactoryMethodComponent {
    @Bean("bb2")
    //@Qualifier("bb2")
    public T110Bean02 b2() {
        return new T110Bean02("t110Bean02");
    }
}
