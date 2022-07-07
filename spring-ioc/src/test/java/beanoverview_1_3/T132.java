package beanoverview_1_3;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 1.3.2. Instantiating Beans
 */
public class T132 {
    @Test
    public void instantiation_with_a_constructor() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beanoverview/instantiation_with_a_constructor.xml");
        System.out.println(ctx.getBean("user"));
    }

    @Test
    public void instantiation_with_a_static_factory_method() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beanoverview/instantiation_with_a_static_factory_method.xml");
        System.out.println(ctx.getBean("clientService"));
    }

    @Test
    public void instantiation_by_using_an_instance_factory_method() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beanoverview/instantiation_by_using_an_instance_factory_method.xml");
        System.out.println(ctx.getBean("clientService"));
    }
}
