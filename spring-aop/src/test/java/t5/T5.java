package t5;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import t5.bean.People;
import t5.config.AppConfig;
import t5.service.MyService;

public class T5 {
    @Test
    public void pure_xml_config_aspect() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("t5/aop.xml");
        People people = ctx.getBean("people", People.class);
        people.doWork();
    }

    @Test
    public void no_pointcut_config_advise_expression_instead() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("t5/advise_expression.xml");
        People people = ctx.getBean("people", People.class);
        people.doWork();
    }

    @Test
    public void advise_around() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("t5/advise_around.xml");
        People people = ctx.getBean("people", People.class);
        people.doWork();
    }

    @Test
    public void specify_args() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("t5/specify_args.xml");
        People people = ctx.getBean("people", People.class);
        people.loopCall("jack", 2);
    }

    @Test
    public void aop_without_xml_config() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MyService service = ctx.getBean("myService", MyService.class);
        service.testMethod();
    }

    @Test
    public void aspectj_annotation_with_xml_config() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("t5/aspect_annotation_with_xml_config.xml");
        MyService service = ctx.getBean("myService", MyService.class);
        service.testMethod();
    }
}
