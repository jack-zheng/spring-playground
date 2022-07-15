# spring-playground

[Spring Core 5.3.21](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html) 官方文档学习记录

## env-setup

简单 setup 一下 spring 测试环境，通过 xml 注入 bean 并测试运行

## spring-ioc

### 1.2 Container Overview

1. 展示了 IoC 的基础模型
2. 使用 IoC 时的核心配置: config(xml, annotation...) + context 实例化
3. 弹性最高的实例化方式: context + reader + refresh()

### 1.3 Bean Overview

1. 开篇说 container 是 bean 管理容器，一下子联想到生成中用到的 AccountBean 结果查了一下发现并没有加任何 @Bean 的注释，会想一下流程应该是 Container 管理了 Service 服务的 bean, 然后通过调用 service 从 DB 返回目标 bean
2. container 中管理的 BeanDefinition 包含如下信息
    * 包路径
    * bean behavioral 配置，如果 scope, lifecycle callback 等
    * bean 依赖
    * 其他配置信息，如链接池的 size limit 等
3. 列出了所有的 bean definition 的 property，后面的文章中有给出例子
    * Class
    * Name
    * Scope
    * Constructor arguments
    * Properties
    * Autowiring mode
    * Lazy initialization mode
    * Initialization method
    * Destruction method
4. 我们可以通过 bean definition 的 class 属性来实例化 bean, 通常有两种做法
    * 反射，等价于 new
    * 通过静态工厂
5. 介绍几中 bean 实例化方式
   * 最常见的直接通过构造方法
   * 通过静态工厂方法
   * 通过实例的方法
6. 想要确定 bean 的运行时类型，建议调用 BeanFactory.getType 方法

> 通过静态工厂方法 vs 通过实例的方法
> 这两者区别这是表现形式不一样，效果都是一样的，生成了想要的 bean. 
> 静态工厂 <bean id="t13static" class="bean.t13.StaticFactoryMethod" factory-method="getT13Bean"/>
> class 为工厂方法所在的类，指定工厂方法，返回的结果则是目标类型的 bean
> 实例方法 
>  <bean id="t13instance" factory-bean="instanceFactoryMethod" factory-method="getT13Bean"/>
>  <bean id="instanceFactoryMethod" class="bean.t13.InstanceFactoryMethod" />
> 先用 bean 标签生成工厂 bean 然后通过 factory-bean 指定这个工厂 bean, 再指定工厂方法即可

### 1.4 Dependencies

当 bean 构建时，容器帮他管理依赖，而不是 bean 自己通过构造或者 Service Locator pattern 管理，这就是控制反转。

DI 的两种主要表现形式: 构造函数 + setter 方法

通过构造函数指定参数时有几种选项：

1. 如果参数类型不同，通过 ref 或者 type 指定即可
2. 通过 index 指定序号
3. 通过 name 指定变量名称

构造函数 vs setter: 对一些 require 的属性，对他们使用构造函数的方式注入。对于 optional 的属性，使用 setter 方式注入

容器处理依赖的逻辑如下

1. ApplicationContext 创建时会初始化所有配置的依赖
2. bean 的依赖以 property，构造函数参数或 静态工厂方法参数的形式存在，当 bean 被创建时，依赖会被处理
3. 每一个 property 或参数，存在的形式要么是具体的值，要么是容器中的 bean
4. 每一个 property 或参数都会从容器中的特殊类型转化到具体知道类型

当容器中 bean 有循环依赖时，会抛出 BeanCurrentlyInCreationException 异常，虽然不是很推荐，但是我们可以用 **setter** 的方式来绕过这个问题

**1.4.2** 常见 bean 属性配置方式

* 原始类型 + String
* bean 类型
* 内部类
* 集合，包括 list, set, map 和 property
* 空字串 + null

c 命名空间和 p 命名空间说白了就是构造器注入和 setter 注入的简化形式。通过添加配置可以实现在同一行中完成设置，简化代码。c 就是 constructor 的缩写，而 p 就是 property 的缩写

**1.4.3** 当类之间的依赖很明显的时候，我们可以使用构造或者 setter 来注入，但是当这个依赖不明显时，我们可以使用 depends-on 属性指定加载顺序

> 比如 classA 初始化时需要用到 propA, 但是业务逻辑上规定 propA 有默认值，但是如果环境有特殊设定，则会在 classB 中做修改。
> 在这种场景下，我们就可以让 classA depends-on 到 classB 来确定这种隐式的依赖

**1.4.4** 懒加载知识点：

1. container 默认是会在容器创建时创建配置的 bean
2. 可以通过 bean 的 lazy-init 属性或者 <beans default-lazy-init="true"> 来配置懒加载
3. 如果一个非懒加载的 bean 对懒加载的 bean 有依赖，那么这个懒加载的 bean 在容器创建时就会被一起创建

**1.4.5** xml 中配置自动加载 bean，视频教程中是在注解之前讲的，顺序有点乱了。
autowiring 说白了就是通过添加这个属性，容器帮你自动匹配需要注入的属性，省的你自己配置了。

autowire 有四种类型，分别是 no, byName, byType, constructor

优点：

1. 减少配置代码
2. 自动匹配，即使以后模型改变，也不需要你改配置

缺点：

1. 基础类型，String, 和 Classes 不能自动注入，这个 by-design 的
2. 相比以前，bean 直接的关系更模糊了，不再精确展示出来
3. 一些工具将不能从容器中提取 bean 之间的关系
4. setter 和构造可能会匹配到多个符合标准的 bean

可以通过 autowire-candidate 属性阻止自动匹配，但是优先级比 byName 低

**1.4.6** Method Injection

设想一种场景，你有两个容器管理的 bean, 分别是 A，B 然后 B 是 A 的一个 autowire 的属性，并且是 prototype 的。这是如果你连续调用 A 打印 B 会发现 B 是不变的，并不符合 prototype 的定义
原因是因为 A 是托管给容器的，他只初始化一次，完成够 A-B 之间的关系就确定了，B 并不会刷新。

解决办法有三种 

* ApplicationContextAware 接口，并不是很推荐，因为会和 spring 代码产生依赖
* method injection，底层使用 CGLIB 产生子类，重写方法
* replaced-method, 这种功能上和 AOP 重叠的，而且替换效率低，更推荐 AOP 实现

看了文档，感觉前面两个的主要意图还是解决 bean 的依赖关系，在方法执行后，返回我么想要的 prototype bean, 也就是 return type 是某个 bean 的方法。如果你只是想改变逻辑还得用 AOP。

### 1.5 Bean Scopes

Spring 框架支持 6 中 scope，其中后四种只有在集成 web-aware 组件之后可用

scope 种类：

1. singleton, 默认类型，容器只生成一个
2. prototype, 原型，每次取都不一样。容器在创建出这种类型的 bean 之后并不会销毁他，需要 client 自己处理，可以通过 bean post processor 实现
3. request
4. session
5. application
6. websocket

### 1.6. Customizing the Nature of a Bean

Spring 提供了丰富的接口，可以让你在 bean 的各个阶段对他做一些定制

**1.6.1.** Lifecycle Callbacks

* InitializingBean, 在容器将所有必要的属性都填充完后，开始执行
* DisposableBean

这两个接口可以让你在 bean 完成构造后和销毁前做一些事情，不过这种做法会对 Spring 产生依赖，推荐使用 JSR-250 中定义的接口 

* @PostConstruct
* @PreDestroy

如果不想用这个，还可以考虑在 xml 总配置

* init-method
* destroy-method

如过想再每一个 bean 中执行某方法，可以在 xml 的 <beans/> 中使用 default-init-method 和 default-destroy-method. 如果 <bean/> 中也指定了 init-method, 那么 default 的会被覆盖

前面这写如果你都配置了，那么执行顺序如下

1. @postConstruct
2. afterPropertiesSet()
3. @Bean(initMethod)
4. @preDestroy
5. destroy()
6. @Bean(destroyMethod)

PS: 如果你指定的名字一样，只会跑一次，比如你 @Bean(initMethod="afterPropertiesSet") 那么 afterPropertiesSet 这个方法只会跑一次，这个有点厉害

给了一些 Lifecycle 相关的接口，但是不清楚怎么用，他只讲了概念

**1.6.2.** ApplicationContextAware and BeanNameAware

如果 bean 在需要用到容器的信息，可以实现 ApplicationContextAware 接口，但是通常不推荐这样做，因为会对容器本身产生依赖，下册。

@Autowire 也可以注入 ApplicationContext，之前有讲过

@BeanNameAware 用来给 bean 取名字，它发生在容器为 bean 解决依赖之后，InitializingBean.afterPropertiesSet() 等 init 方法之前 

**1.6.3.** Other Aware Interfaces

* ApplicationContextAware
* ApplicationEventPublisherAware
* BeanClassLoaderAware
* BeanFactoryAware
* BeanNameAware
* LoadTimeWeaverAware
* MessageSourceAware
* NotificationPublisherAware
* ResourceLoaderAware
* ServletConfigAware
* ServletContextAware

### 1.7. Bean Definition Inheritance

感觉像是讲了一些父子关系的类在 xml 中怎么配置的问题。。。可能以后看源码的时候，涉及到父子关系的 bean definition 时会用到

### 1.8. Container Extension Points

**1.8.1.** Customizing Beans by Using a BeanPostProcessor

可以自定义多个，结合 @Order 进行排序。在容器生成 bean 之后执行。如果有多个容器(hierarchies 的情况)，BeanPostProcessor 只能操作自己所属容器的 bean.

如果要操作 bean definition, 需要用到 BeanFactoryPostProcessor

我们可以按照定义普通 bean 那样在 config 文件中注册一个 BeanPostProcessor

**1.8.2.** Customizing Configuration Metadata with a BeanFactoryPostProcessor

使用方式和 BeanPostProcessor 相同，不过 BeanFactoryPostProcessor 是作用于 bean definition 的，而不是 bean. Spring 自定义了一些 BeanFactoryPostProcessor， 比如

* PropertyOverrideConfigurer
* PropertySourcesPlaceholderConfigurer

Bean(Factory)PostProcessor 默认是不支持 lazy 模式，就算配置了，加载的时候也默认忽略. 不过他给的这些例子不是很看的懂，因为不能执行，得自己找得实例看看

**1.8.3.** Customizing Instantiation Logic with a FactoryBean

生成 bean 的一种方式而已，可以配合 @Bean + @Configuration/@Component 使用

### 1.9. Annotation-based Container Configuration

Container 创建过程中 Annotation 标签先于 xml 处理，所以 xml 中的配置会覆盖注解的配置

如何启动注解模式：配置文件中 beans 加入 context 相关属性, 并在主体中加入 `<context:annotation-config/>` 即可
由于注解是通过反射实现的，pojo 中甚至可以不写 setter 方法

当添加 `<context:annotation-config/>` 后下面这些元素会自动注册

* ConfigurationClassPostProcessor
* AutowiredAnnotationBeanPostProcessor
* CommonAnnotationBeanPostProcessor
* PersistenceAnnotationBeanPostProcessor
* EventListenerMethodProcessor

[Attention]: 该配置只会扫描同一个 context 中添加有 @Autowire 的 bean, 比如这个配置是添加在 WebApplicationContext 下的，
那么他是不会去扫描 service 中带有 @Autowire 的 bean 的。

这段话和我工作中遇到的那个 Aspect 不能扫描 controller 中方法的原因应该是一样的。

**1.9.1** @Required 强制规定 config 的时候属性必须配置，不然抛错。spring 5.1 时名义上已经 deprecate 这个标签了，推荐使用构造函数或者 required 类型的 setter 强制这中关系

这个标签使用前需要先注册 RequiredAnnotationBeanPostProcessor 不然不生效。不过默认好像已经注册了。它是放在方法上的，**不能**放在属性上。

**1.9.1** @Autowire

@Autowire 和 xml 里的 autowire 一样，可以直接加在构造函数上, 也可以加在方法上，而且方法名字不需要尊从 setter 格式。还可以直接加在成员变量上面。还支持集合类型的注入，如 list, map 等，和 xml 可以对应起来。

PS：spring 4.3 之后，如果只有一个构造，则不需要加这个注解。如果有多个构造，且没有无参构造，则必须指定。

@Autowire 的注入规则挺奇怪的，如果一个容器中有两个以上匹配的 bean, 他会使用 byName 去匹配，找不到就抛错。如果只有一个，那么他会使用 byType 匹配，可以说兼容性很强了。

@Autowire 有一个 required 属性，这个和我预想的逻辑不一样，我原本以为，加了这个那么就不会填充。
结果不是，他的处理逻辑是，如果加了并且容器中没有，就不会抛错，而是设置为 null。
如果没加，初始化的时候就直接抛异常了。

和这个 required 属性等价的标签是 Java 原生的 Optional 标签和 @Nullable 标签

@Autowired, @Inject, @Value, and @Resource 是通过 BeanPostProcessor 处理的。

**1.9.4** Fine-tuning Annotation-based Autowiring with Qualifiers

通过使用这个标签可以让我们更精确的匹配需要注入的 bean。这个标签的默认值是**空字串**，所以一定要给值啊，不然找不到目标了。他可以加在成员变量上，方法上或者方法参数里

后面是如何定制自己的 Qualifier 的章节

**1.9.7** Injection with @Resource

@Resource 也可以实现 @Autowire 的效果，他可以通过 name 属性达到 byName 的效果

搜索规则：

1. 指定 name 则按 byName 类似规则找
2. 如果没有指定 name 就按 type 
3. 如果匹配有多个结果，看这些 bean 找和属性重名的那个，没有就抛错

[Attention] 默认这个注解找不到，自动添加找到了 jsr250-api 这个 jar，还是失败，搜了一下需要用 javax.annotation-api 这个 jar。妈蛋，这个点在这章的最后一段有提到，而且是在 java11 的时候从 jdk 中移除的

**1.9.8** Using @Value

这个是从 config 文件中取值用的，结合 Java config 使用

**1.9.9** Using @PostConstruct and @PreDestroy

@PostConstruct 会在 constructor 之后执行

### 1.10. Classpath Scanning and Managed Components

@Component 可以用来标注组件，他还拍分出三类标签用于 MVC 分层分别是

* DAO - @Repository
* Service - @Service
* Controller - @Controller

**1.10.3.** Automatically Detecting Classes and Registering Bean Definitions

这章以前，我们需要在 xml 中声明 bean, 这里我们可以通过添加 `<context:component-scan` 这个配置实现自动扫描

PS: 这个注解会默认添加 annotation-config 功能

我们也可以使用纯的 Java config 类来代替 xml, 不过采用这种方法时，context 类要换成 annotation 类型的(这里 config 类啥都不加就能 work...)

**1.10.4.** Using Filters to Customize Scanning

可以定制 filter 规则，暂时不用，pass

**1.10.5.** Defining Bean Metadata within Components

Component 也可以用来生成 bean, 但是本地发现一个问题，用 qualifier 指定的 id 并不生效，倒是直接通过 @Bean("b2") 的方式指定的 name 是生效的

@Component 的 @Bean 方法和 @Configuration 的 @Bean 方法的区别是 Configuration bean 方法可以结合 CGLIB 使用，这里他的描述的一下概念我不是很能理解，应该是说的 AOP 相关的点。

**1.10.6.** Naming Autodetected Components

在没有特殊指定 bean name 的情况下，默认名字是由 BeanNameGenerator 这个类生成的，规则是**首字母小写的驼峰标识**，如果想自定义可以实现 BeanNameGenerator 这个接口并在配置中指定

**1.10.7.** Providing a Scope for Autodetected Components

实体类或工厂方法中可以标注 @Scope，并且我们可以自定义 scope 类型

**1.10.8.** Providing Qualifier Metadata with Annotations

@Component 修饰的类上可以 apply @Qualifier 标签指定 name

**1.10.9.** Generating an Index of Candidate Components

一种加速 component 扫描的方法，我肯定用不到，看上去是项目非常巨大的时候才会去使用

### 1.12. Java-based Container Configuration

**1.12.1.** Basic Concepts: @Bean and @Configuration

@Bean 指代一个方法具有配置，初始化, 实例化容器中的 bean 的能力。能力上等价于 xml 中的 bean 标签

@Configuration 则指代它是 bean definition 的 source，并且允许 @Bean 方法之间互相调用。实现很简单

```java
@Configuration
public class AppConfig {
    @Bean
    //@Scope("prototype")
    public User user() {
        return new User();
    }
}
```
@Bean + @Configuration = Full 模式; @Bean + @Component = Lite 模式; 并且少了各自的优缺点

**1.12.2.** Instantiating the Spring Container by Using AnnotationConfigApplicationContext

AnnotationConfigApplicationContext 扫描时会扫描 @Configuration, @Component 和 JSR-330 的标签

AnnotationConfigApplicationContext 不仅可以接收 @Configuration 还可以接收 @Component 和 JSR-330

和 xml 那种注册多个 xml 然后 refresh 的方式相同，annotation 的 context 也支持这种形式

@Configuration 标签可以配合 @ComponentScan 使用，相对的 AnnotationConfigApplicationContext 也有 scan 方法

**1.12.3.** Using the @Bean Annotation

@Bean 支持的属性

* init-method
* destroy-method
* autowiring
* name

@Bean 还可以配合接口的 default 方法使用

@Bean 修饰的方法可以带参数，参数的匹配方式和构造函数那套一致

@Bean 可以指定 lifecycle 相关的属性，这个要等看了前面介绍 lifecycle 的相关章节才有感觉

@Bean 可以和 @Scope 搭配指定作用域

**1.12.4.** Using the @Configuration annotation

inter-bean 类型的依赖甚至不需要在方法中以参数的形式暴露

look-up method 的 Java 实现案例，如果一个 singleton-scoped bean 对 prototype-scope 类型的类有依赖，可以用这种方式

两个 @Bean 方法依赖同一个 bean 的时候，生成的对象，成员变量是同一个。描述比较模糊，但是 further information ... 这个章节的例子说的比较清楚

**1.12.5.** Composing Java-based Configurations

和 xml 一样，@Configuration 可以和 @Import 搭配使用，将多个 config 类组合到一起

四组例子，展示了如何在多个 config 类之间，清楚的展现出依赖方式

通过 @Profile 标签，控制只在某些情况下才生成 bean

最后介绍了 xml + @Configuration 混用的情况，可以以 xml 为基础，通过 `<context:annotation-config/>` 或者 `<context:component-scan/>` + <bean/> 将 config 类引入。
也可以在 Java config 类中通过 @ImportResource 实现集成

### 1.14. Registering a LoadTimeWeave

不是很懂，但看着像是 类加载期间 用来做切面的

### 1.15. Additional Capabilities of the ApplicationContext

BeanFactory 类提供基础的操作，管理 bean 的功能，ApplicationContext 则是在此基础上提供了更多应用层面的能力

context 比 factory 增加了如下功能

* i18n 支持 - MessageResource
* 访问资源 - ResourceLoader
* 事件 - Event
* 加载多个 context，比如区分 web layer 和 service layer，通过 HierarchicalBeanFactory 接口实现

PS: Bizx 里面 AOP 不能识别 @Controller 里面的内容可能就是应为这个

介绍 message 的应用，我现在肯定用不到

介绍 event 的使用，通过实现 ApplicationListener 接口，我们可以监听 context 事件。内置的事件有

* ContextRefreshedEvent
* ContextStartedEvent
* ContextStoppedEvent
* ContextClosedEvent
* RequestHandledEvent
* ServletRequestHandledEvent

** 1.16.1.** BeanFactory or ApplicationContext

两者对比，并指出优缺点

## Resources

JDK 提供的 URL 类来处理资源相关问题，但是兼容性并不好，很多情况不能兼容，spring 提供了 Resource 接口来解决这个问题

### 2.2 The Resource Interface

package is meant to be a more capable interface for abstracting access to low-level resources.

### 2.3. Built-in Resource Implementations

* UrlResource
* ClassPathResource, 以 `classpath:` 开头
* FileSystemResource
* PathResource, 相比前者更高效
* ServletContextResource
* InputStreamResource
* ByteArrayResource
  
### 2.4. The ResourceLoader Interface

用来返回 Resource 的接口， ClassPathXmlApplicationContext 调用 getResource 返回 ClassPathResource，FileSystemXmlApplicationContext 返回 FileSystemResource，
WebApplicationContext 返回 ServletContextResource。你也可以指定前缀来指定返回类型 比如 classpath:, file://, https://.

### 2.5. The ResourcePatternResolver Interface

ResourceLoader 的扩展接口，指定 pattern 返回符合标准的接口

### 2.6. The ResourceLoaderAware Interface

一个回掉接口知道一个 component 期望接收一个 ResourceLoader 引用。容器在处理带有这种接口的 bean 时会将在初始化的时候将 ResourceLoader 注入到这个 bean 中。

当然你也可以用 @Autowire 或者在 bean 的构造函数中指定 ResourceLoader 或者用 setter 方法来达到同样的效果。

列出了一写 xml 中配置 resource 的 sample。

### 2.8. Application Contexts and Resource Paths

一些匹配方式的应用，没什么新意

## Aspect Oriented Programming with Spring

### 5.1 AOP Concepts

* Aspect 实现切面逻辑的类，带有 @Aspect 注解的那些
* Join point: 需要处理的目标方法
* Advice: 处理方式，包裹 around, before, after
* Pointcut: 目标方法的匹配规则
* Introduction: ?
* Target object: 切面的作用对象
* AOP proxy: 框架创建的实现切面的对象
* Weaving: 将切面和其他对象关联起来

Advice 类型：

* before
* after
* after throwing
* after (finally) advice
* around advice

### 5.2. Spring AOP Capabilities and Goals

* Spring AOP 为纯 Java 实现
* 目前只支持对方法做 advice，field 不支持
* 并没有提供 AOP 的全面支持，只实现 IoC 相关的部分

### 5.3. AOP Proxies

Spring AOP 默认使用 JDK 动态代理 + interface 实现 AOP 支持。同时也支持 CGLIB 代理。

### 5.4. @AspectJ support

这里指的是 AspectJ 项目引入的一些 annotation。

通过 @EnableAspectJAutoProxy 或者 <aop:aspectj-autoproxy/> enable AOP 功能

PS: 可以通过 xml, @Bean + @Configuration 或者 classpath scan 的方式注册 aspect bean。如果通过扫描的方式注册，那么 Aspect 类还需要添加 @Component 注解

**5.4.3.** Declaring a Pointcut

匹配规则由两部分组成： 普通 void 方法 + @Pointcut 表达式

```java
@Pointcut("execution(* transfer(..))") // the pointcut expression
private void anyOldTransfer() {} // the pointcut signature
```

Spring AOP 支持如下标签

* execution，匹配方法
* within，匹配包路径
* this，proxy 实现了某接口
* target，target object implements
* args，匹配方法参数
* @target，目标 object 由 xx 注解
* @args，参数带有 xx 注解
* @within，the target object has an xx annotation
* @annotation, the executing method has an @xx annotation
* bean, 容器中 xx bean 的所有方法，支持模糊匹配

PS: 难怪我的那个 perf 注解加了之后老是启动失败，注解加错了，如果是方法带注解，要使用 @annotation

pointcut 支持组合

pointcut 格式： execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern)
throws-pattern?)

关于匹配性能，匹配分三种类型

* Kinded designators select a particular kind of join point: execution, get, set, call, and handler.
* Scoping designators select a group of join points of interest (probably of many kinds): within and withincode
* Contextual designators match (and optionally bind) based on context: this, target, and @annotation

scoping 类型是最快的，所以尽量用 scoping + 其余 的方式组织你的匹配规则

项目中为了测试 AOP 需要加入新的 dependency

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-aspects -->
<dependency>
   <groupId>org.springframework</groupId>
   <artifactId>spring-aspects</artifactId>
   <version>5.3.21</version>
</dependency>
```

**5.4.4. Declaring Advice**

Advice 注解中的内容可以是 pointcut 表达式，也可以是 匹配规则

@After 中你必须提供处理正常和异常情况的解决方案。

**5.4.7.** An AOP Example

举了一个 retry 的例子，可以魔改一下试试

### 5.5. Schema-based AOP Support

xml 中定义 Aspect 相关的类时，pointcut 和 aspect 标签可以是平级，也可以是从属关系。写了一些 xml 配置 aop 的例子，挺好理解的，倒是官方文档的说明太松散了，学起来效率太低。

### 5.6. Choosing which AOP Declaration Style to Use

* 没必要采用 Full AspectJ
* xml + AOP 共能支持不全，而且会导致配置分散
* @AspectJ 更推荐
  
On balance, the Spring team prefers the @AspectJ style for custom aspects beyond simple configuration of enterprise services.

## 5.8. Proxying Mechanisms

Spring AOP 实现分两种：JDK dynamic proxies + CGLIB, 前者只适用于实现有接口，后者则是 public，protected 的方法都能适用。

可以通过 `<aop:config proxy-target-class="true">` 强制走 CGLIB 路线。

PS: aspectj-autoproxy 什么意思？

说实话，他说的那个机制部分没怎么看懂

## 5.9. Programmatic Creation of @AspectJ Proxies

暂时不重要，pass

## 5.10. Using AspectJ with Spring Applications

被 `<aop:aspectj-autoproxy/>` 这个标签误导了好久，其实就集中情况。默认情况下 Spring 是不支持 @Aspect 等 AspectJ 标签的，而且 spring 中不用这些标签就能实现 AOP 功能。

纯 Spring 配置 AOP, 只需要定义普通的 Java 类，不需要加任何注解。然后在 xml 中加入 aop 检验的 xsd 并配置切面关系即可。参考 aop.xml 相关的 UT

不过官方文档也说了，spring + aspectj 注解是最佳实践，如果你想在 Java 类中使用注解，但是通过 xml 配置 + ClassPathXmlApplicationContext 启动，
则需要在 xml 中引入 aop.xsd 和 context.xsd 并且添加对应的扫描配置和代理配置

* <aop:aspectj-autoproxy/>
* <context:annotation-config/>
* <context:component-scan base-package="t5.service"/>

如果是纯 Java 实现，其实就是新建一个 config 类，并把上面的 xml 配置通过注解转化一下

* @Configuration
* @EnableAspectJAutoProxy
* @ComponentScan

后面是一些非 runtime 的 AOP 实现，用的 load time weaver 做例子, 是类加载期间的织入, 暂时用不到就不看了

## 6. Spring AOP APIs

For common applications, we recommend the use of Spring AOP with AspectJ pointcuts as described in the previous chapter.

开篇明义，说是推荐使用 spring + aspectj 配合模式。后面介绍了很多概念性的东西，貌似没什么好看的