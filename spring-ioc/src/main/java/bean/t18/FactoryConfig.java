package bean.t18;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfig {
    @Bean
    public T18FactoryBean t18FactoryBean() {
        return new T18FactoryBean();
    }

    @Bean
    public T18Bean t18() throws Exception {
        return t18FactoryBean().getObject();
    }
}
