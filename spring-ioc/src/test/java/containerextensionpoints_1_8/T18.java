package containerextensionpoints_1_8;

import bean.t18.FactoryConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class T18 {
    @Test
    public void hello_world_of_beanPostProcessor() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("containerextensionpoints/helloworld_of_beanPostProcessor.xml");
        System.out.println(ctx.getBean("t18"));
    }

    @Test
    public void factory_bean_test() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(FactoryConfig.class);
        System.out.println(ctx.getBean("t18"));
    }
}
