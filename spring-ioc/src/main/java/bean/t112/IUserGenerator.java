package bean.t112;

import bean.User;
import org.springframework.context.annotation.Bean;

public interface IUserGenerator {
    @Bean("uu")
    default User getUser() {
        User u = new User();
        u.setName("Jack");
        return u;
    }
}
