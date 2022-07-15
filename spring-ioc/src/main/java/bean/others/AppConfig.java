package bean.others;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ProtoTestBean protoTestBean() {
        ProtoTestBean protoTestBean = new ProtoTestBean();
        protoTestBean.setName("proto");
        return protoTestBean;
    }

    @Bean
    public ProtoService protoService() {
        return new ProtoService();
    }
}
