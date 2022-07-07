package bean.t112;

import bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    //@Scope("prototype")
    public User user() {
        return new User();
    }

    @Bean
    public ParamBean paramBean() {
        return new ParamBean();
    }

    @Bean
    public Out out(ParamBean paramBean) {
        return new Out(paramBean);
    }

    @Bean
    public Out out2() {
        return new Out(paramBean());
    }

    @Bean
    public T112Service service1() {
        T112Service service = new T112Service();
        service.setParamBean(paramBean());
        return service;
    }

    @Bean
    public T112Service service2() {
        T112Service service = new T112Service();
        service.setParamBean(paramBean());
        return service;
    }
}
