package others;

import bean.others.AppConfig;
import bean.others.ProtoService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrototypeLifecycleTest {
    @Test
    public void get_bean_from_config() {
        // 实验失败，感觉是应为 bean 通过 autowire 和 service 绑定了
        // 而 service 是常驻 context 中的，那么 bean 的引用一直都在，自然不会 GC
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.getBean("protoService", ProtoService.class).invokeBean();
        while(true) {}
    }
}
