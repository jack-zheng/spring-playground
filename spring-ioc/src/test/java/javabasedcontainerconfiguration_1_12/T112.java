package javabasedcontainerconfiguration_1_12;

import bean.User;
import bean.t110.FactoryMethodComponent;
import bean.t112.AppConfig;
import bean.t112.Generator;
import bean.t112.T112Service;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class T112 {
    @Test
    public void get_bean_from_config() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(ctx.getBean("user", User.class));
        System.out.println(ctx.getBean("user") == ctx.getBean("user"));
    }

    @Test
    public void get_bean_from_component() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(FactoryMethodComponent.class);
        System.out.println(ctx.getBean("bb2"));
    }

    @Test
    public void get_bean_using_register() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        System.out.println(ctx.getBean("user"));
    }

    @Test
    public void bean_in_interface() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Generator.class);
        System.out.println(ctx.getBean("uu"));
    }

    @Test
    public void bean_with_dependency() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(ctx.getBean("out"));
    }

    @Test
    public void bean_with_dependency2() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(ctx.getBean("out2"));
    }

    @Test
    public void further_more() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        T112Service service1 = ctx.getBean("service1", T112Service.class);
        T112Service service2 = ctx.getBean("service2", T112Service.class);

        System.out.println(service1.hashCode());
        System.out.println(service2.hashCode());
        System.out.println(service1.getParamBean() == service2.getParamBean());
    }
}
