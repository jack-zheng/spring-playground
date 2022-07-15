package dependencies_1_4;

import bean.t14.T14BeanAAware;
import bean.t14.T14BeanAMethodInjection;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Dependency Injection
 */
public class T146 {
    @Test
    public void method_injection_scenario() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t146/method_injection.xml");
        System.out.println(ctx.getBean("t14BeanA"));
        System.out.println(ctx.getBean("t14BeanA"));
        // T14BeanA{name='a', t14BeanB=T14BeanB{hash='1951963537'}}
        // T14BeanA{name='a', t14BeanB=T14BeanB{hash='1951963537'}}
    }

    @Test
    public void injection_using_aware() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t146/injection_using_aware.xml");
        System.out.println(ctx.getBean("t14BeanAAware", T14BeanAAware.class).getT14BeanB());
        System.out.println(ctx.getBean("t14BeanAAware", T14BeanAAware.class).getT14BeanB());

        System.out.println(ctx.getBean("t14BeanB"));
        System.out.println(ctx.getBean("t14BeanB"));
    }

    @Test
    public void method_injection() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t146/method_injection.xml");
        System.out.println(ctx.getBean("t14MethodInjection", T14BeanAMethodInjection.class).getT14BeanB());
        System.out.println(ctx.getBean("t14MethodInjection", T14BeanAMethodInjection.class).getT14BeanB());
    }

    @Test
    public void method_injection_annotation() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dependencies/t146/method_injection_annotation.xml");
        System.out.println(ctx.getBean("t14MethodInjection", T14BeanAMethodInjection.class).getT14BeanB());
        System.out.println(ctx.getBean("t14MethodInjection", T14BeanAMethodInjection.class).getT14BeanB());
    }
}
