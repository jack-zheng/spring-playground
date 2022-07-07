package dependencies_1_4;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Dependency Injection
 */
public class T143 {
    @Test
    public void using_depends_on() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t143/using_depends_on.xml");
        System.out.println(ctx.getBean("classA"));
    }
}
