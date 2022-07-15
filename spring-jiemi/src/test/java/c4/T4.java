package c4;

import beans.DatePropertyEditor;
import beans.DatePropertyEditorRegistrar;
import beans.DowJonesNewsListener;
import beans.DowJonesNewsPersister;
import beans.FXNewsProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class T4 {
    @Test
    public void get_bean_using_BeanFactory() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("c4/c4_1.xml"));
        FXNewsProvider provider = bf.getBean("djNewsProvider", FXNewsProvider.class);
        System.out.println(provider);
    }

    /**
     * 效果和 xml 配置一样，只不过配置通过写代码的形式完成
     */
    @Test
    public void BeanFactory_bind_via_code() {
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();

        // 定义需要注入的 definition
        AbstractBeanDefinition newsProvider = new RootBeanDefinition(FXNewsProvider.class);
        AbstractBeanDefinition newsListener = new RootBeanDefinition(DowJonesNewsListener.class);
        AbstractBeanDefinition newsPersister = new RootBeanDefinition(DowJonesNewsPersister.class);

        // 注册 definition
        beanRegistry.registerBeanDefinition("djNewsProvider", newsProvider);
        beanRegistry.registerBeanDefinition("djNewsListener", newsListener);
        beanRegistry.registerBeanDefinition("djNewsPersister", newsPersister);

        // 指定绑定形式
        ConstructorArgumentValues argValues = new ConstructorArgumentValues();
        argValues.addIndexedArgumentValue(0, newsListener);
        argValues.addIndexedArgumentValue(1, newsPersister);
        newsProvider.setConstructorArgumentValues(argValues);

        FXNewsProvider provider = (FXNewsProvider) beanRegistry.getBean("djNewsProvider");
        System.out.println(provider);
    }

    @Test
    public void BeanFactory_bind_via_xml() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("c4/c4_1.xml");

        FXNewsProvider provider = (FXNewsProvider) beanFactory.getBean("djNewsProvider");
        System.out.println(provider);
    }

    @Test
    public void get_prop_using_PropertyPlaceholderConfigurer() {
        ConfigurableListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("c4/PropertyPlaceholderConfigurer_sample.xml"));

        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("c4/admin.properties"));
        configurer.postProcessBeanFactory(beanFactory);

        System.out.println(beanFactory.getBean("admin"));
    }

    @Test
    public void get_prop_using_PropertyPlaceholderConfigurer_ApplicationContext() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("c4/PropertyPlaceholderConfigurer_sample.xml");
        System.out.println(ctx.getBean("admin"));
    }

    @Test
    public void get_prop_using_PropertyOverrideConfigurer() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("c4/PropertyOverrideConfigurer_sample.xml");
        System.out.println(ctx.getBean("admin"));
    }

    @Test
    public void get_DateFoo() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("c4/CustomEditorConfigurer_sample.xml");
        System.out.println(ctx.getBean("dateFoo"));
    }

    @Test
    public void get_DateFoo_using_BeanFactory() throws ParseException {
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("c4/CustomEditorConfigurer_sample.xml"));

        // 从代码结构上可以看出来，其实就是将 xml 中的配置用代码重写一遍
        DatePropertyEditorRegistrar registrar = new DatePropertyEditorRegistrar();
        registrar.setPropertyEditor(new DatePropertyEditor());
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        configurer.setPropertyEditorRegistrars(new PropertyEditorRegistrar[] {registrar});
        configurer.postProcessBeanFactory(factory);

        System.out.println(factory.getBean("dateFoo"));
    }
}
