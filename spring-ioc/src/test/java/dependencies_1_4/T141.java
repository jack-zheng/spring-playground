package dependencies_1_4;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Dependency Injection
 */
public class T141 {
    @Test
    public void circular_dependencies() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t141/circular_dependencies.xml");
        System.out.println(ctx.getBean("circularA"));
    }

    /**
     * 如果想要使用 setter 需要先将原有的构造函数注销
     */
    @Test
    public void circular_dependencies_workaround_setter() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t141/circular_dependencies_workaround_setter.xml");
        System.out.println(ctx.getBean("circularA"));
    }
}
