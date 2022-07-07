package bean.t16;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class T16Config {
    //@Bean(name="t1602", initMethod = "init", destroyMethod = "destroy")
    //public T16Bean02 getT16Bean02() {
    //    return new T16Bean02();
    //}
    //
    //@Bean("t16")
    //public T16Bean getT16Bean() {
    //    return new T16Bean();
    //}

    @Bean(name="t1603", initMethod = "init", destroyMethod = "destroy")
    public T16Bean03 getT16Bean03() {
        return new T16Bean03();
    }
}
