package dependencies_1_4;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Dependency Injection
 */
public class T144 {
    @Test
    public void bean_with_lazy_prop_will_create_later() {
        // lazy1 构造被调用，lazy2 没有被调用
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t144/lazy_initialized_bean.xml");
    }

    @Test
    public void lazy_bean_will_initialized_when_eager_bean_depends_on_it() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t144/eager_bean_depends_on_lazy_bean.xml");
    }
}
