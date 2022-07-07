package annotationbasedcontainerconfiguration_1_9;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class T19 {
    @Test
    public void instantiation_with_a_constructor() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("annotationbasedcontainerconfiguration/eng_setup.xml");
        System.out.println(ctx.getBean("computer"));
    }

    @Test
    public void t192_autowire_on_constructor() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("annotationbasedcontainerconfiguration/autowire.xml");
        System.out.println(ctx.getBean("auto01"));
    }

    @Test
    public void t197_inject_with_resource() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("annotationbasedcontainerconfiguration/resource.xml");
        System.out.println(ctx.getBean("computer"));
    }

    @Test
    public void t199_postConstruct() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("annotationbasedcontainerconfiguration/postconstruct.xml");
        System.out.println(ctx.getBean("keyboard"));
    }
}
