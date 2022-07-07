package beanscope_1_5;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class T151 {
    @Test
    public void singleton_vs_prototype() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beanscope/singleton_vs_prototype.xml");
        // prototype, false
        System.out.println(ctx.getBean("user1") == ctx.getBean("user1"));
        // singleton, true
        System.out.println(ctx.getBean("user2") == ctx.getBean("user2"));
    }
}
