package dependencies_1_4;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Dependency Injection
 */
public class T142 {
    @Test
    public void dependencies_and_configuration_in_detail() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t142/dependencies_and_configuration_in_detail.xml");
        System.out.println(ctx.getBean("student"));
    }

    @Test
    public void c_p_namespace() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t142/c_p_namespace.xml");
        System.out.println(ctx.getBean("address1"));
        System.out.println(ctx.getBean("address2"));
    }
}
