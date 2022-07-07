package customizingthenatureofabean_1_6;

import bean.t16.T16Config;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class T16 {
    @Test
    public void initializingBean_disposableBean_interface_test() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(T16Config.class);
        System.out.println(ctx.getBean("t16"));
        ctx.close();
    }

    @Test
    public void initMethod_destroyMethod_test() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(T16Config.class);
        System.out.println(ctx.getBean("t1602"));
        ctx.close();
    }

    @Test
    public void postConstruct_preDestroy_test() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(T16Config.class);
        System.out.println(ctx.getBean("t1603"));
        ctx.close();
    }
}
