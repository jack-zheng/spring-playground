# Spring 揭秘

## 第4章 Spring 的 IoC 容器之 BeanFactory

BeanFactory 其实有两重属性，一种是 factory，即提供 getBean，isPrototype 等 bean 相关能里，另一种是 registry 能力。通过他可以将 xml, 代码等配置的 definition 信息注册进去，用来后续生成 bean

Spring 的 IoC 容器生成 bean 的过程，可以分为两个阶段，分别是 启动阶段 和 实例化阶段

启动阶段

* 加载配置信息
* 解析配置信息，装配 bean definition
* 将 definition 信息注册是 BeanDefinitionRegistry 中

实例化阶段

* 更具 definition 实例化对象
* 注入依赖
* 处理回掉函数

系统内置的一些典型 BeanFactoryPostProcessor

* PropertyPlaceholderConfigurer, 一些情景下，我们会将配置信息放入 properties 文件，而不是 xml 中，这时可以用它来解析。支持解析 `${}` 占位符 
* PropertyOverrideConfigurer，覆盖默认值, 工作模式和上面的那个一样
* CustomEditorConfigurer，不对 BeanDefinition 做任何改动，用来处理 definition 类型转化问题

PropertyPlaceholderConfigurer 使用，简单来说，就是 properties 中存放自定义的属性，然后在配置的其他 bean 中通过 ${prop.key} 的形式就能实现替换. 
当使用 BeanFactory 时需要显示声明 configurer 并配置匹配关系，ApplicationContext 则不需要

* BeanFactory 默认会延迟初始化，如果需要实例化的 bean 对其他 bean 有依赖，依赖的 bean 也会实例化
* ApplicationContext 启动之后自动实例化 bean

bean 实例化过程：

* 实例化 bean 对象
* 设置属性
* 处理 Aware 接口
* BeanPostProcessor 前置处理
* 检查 InitializingBean 接口，调用 afterPropertiesSet 方法
* init-method
* BeanPostProcessor 后置处理
* 注册 Destruction 接口
* 容器使用中
* 是否实现 DisposableBean 接口
* 是否配置自定义 destroy 方法

容器内部通过 策略模式 决定采用何种方式初始化 bean, 方式有反射或者 CGLIB 修改字节码。实例化后，结果包装在 BeanWrapper 中返回。

BeanWrapper 继承了 PropertyAccessor 和 PropertyEditorRegistry 等接口方便属性设置，类型转换等。便面了反射设置属性，反射太繁琐。不过底层应该还是反射，后面可以再仔细看看

检查是否实现 Aware, 是的话注入依赖实例

BeanPostProcessor 包含两个方法，一个是 before initialization， 另一个是 after。典型例子是 ApplicationContextAwareProcessor，通过他我们可以将 Aware 的内容 set 进去。

## 第5章 Spring IoC 容器 ApplicationContext

### 5.1 统一资源加载策略

* 资源统一用 Resource 表示
* ResourceLoader 表示加载的实体，一次只加载一个资源
* ResourcePatternResolver 批量查找资源，可以是文件系统，可以是 jar 中的资源

如果是自己的类中需要用到这个 resolver，可以是使用注入或者 ResourceLoaderAware。

### 5.2 国际化信息支持