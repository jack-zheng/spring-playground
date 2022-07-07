package classpathscanningandmanagedcomponents_1_10;

import bean.t110.config.MyConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class T110 {
    @Test
    public void auto_scan_config_in_xml() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpathscanningandmanagedcomponents/auto_scan.xml");
        System.out.println(ctx.getBean("t110Bean"));
    }

    @Test
    public void auto_scan_config_in_java() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println(ctx.getBean("t110Bean"));
    }

    @Test
    public void defining_bean_metadata_within_components() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println(ctx.getBean("bb2"));
    }
}
