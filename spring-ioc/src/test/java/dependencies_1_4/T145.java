package dependencies_1_4;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Dependency Injection
 */
public class T145 {
    @Test
    public void autowiring_collaborators() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t145/autowiring_collaborators.xml");
        //System.out.println(ctx.getBean("people1"));
        System.out.println(ctx.getBean("people2"));
        System.out.println(ctx.getBean("people3"));
    }

    @Test
    public void autowiring_collaborators_by_constructor() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t145/autowiring_collaborators_by_constructor.xml");
        System.out.println(ctx.getBean("cater"));
    }

    @Test
    public void exclude_bean_from_autowiring() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t145/exclude_a_bean_from_autowiring.xml");
        System.out.println(ctx.getBean("people"));
    }
}
