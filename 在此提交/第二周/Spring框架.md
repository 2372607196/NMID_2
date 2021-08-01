## Spring框架

### 框架简介

- 什么是框架
  - 具有约束性的去支撑我们实现各种功能的半成品项目
- MVC框架
  - struts1,struts2,springMVC
- 持久层框架
  - hibernate//不用写sql语句，mybatis//要写sql语句
- 整合型框架，设计型框架
  - spring

### Spring框架介绍

- Spring是一个开源框架
- 使用Spring，JavaBean就可以实现很多以前要靠EJB才能实现的功能
- Spring是一个**IOC**和**AOP**容器框架
- Spring的优良特性
  - **非侵入性**：基于Spring开发的应用中的对象可以不依赖于Spring的API
  - **依赖注入**：DI——Dependency Injection，反转控制(IOC)最经典的实现
  - **面向切面编程**：Aspect Oriented Programming——AOP
  - **容器**：Spring是一个容器，因为它包含并且管理应用对象的生命周期
  - **组件化**：Spring实现了使用简单的组建配置组合成一个复杂的应用。在Spring中可以使用XML和Java注解组合这些对象
  - **一站式**：在IOC和AOP的基础上可以整合各种企业应用的开源框架和优秀的第三方类库(实际上Spring自身也提供了表述层的SpringMVC和持久层的 Spring JDBC)

### Spring入门

![62726810755](D:\学习资料\高级篇\Spring程序开发步骤.png)

- 1.导入Spring开发的基本包坐标

  2.编写Dao接口和实现类

  3.创建Spring核心配置文件

  4.在Spring配置文件中配置UserDaoImpl

  5.使用Spring的API获得Bean实例

#### Spring配置文件

##### 1、Bean标签基本配置

- 用于配置对象交由**Spring**来创建
- 默认情况下它调用的是类中的**无参构造函数**，如果没有无参构造函数则不能成功

##### 2、Bean标签范围配置

![62727162320](D:\学习资料\高级篇\scope范围.png)

- 当scope的取值为**singleton**时

  bean的实例化个数：1个

  bean的实例化时机：当Spring核心文件被加载时，实例化配置的Bean实例

  bean的生命周期：

  - 对象创建：当应用加载时，创建容器时，对象就被创建了
  - 对象运行：只要容器在，对象就一直或者
  - 对象销毁：当应用卸载，销毁容器时，对象就被销毁了

- 当scope的取值为**prototype**时

  bean的实例化个数：多个

  bean的实例化时机：当调用getBean()方法时实例化Bean

  bean的生命周期：

  - 对象创建：当使用对象时，创建新的对象实例
  - 对象运行：只要对象在使用中，就一直活着
  - 对象销毁：当对象长时间的不用时，被Java的垃圾回收器回收了

##### 3、Bean的生命周期

- **init-method**：指定类中的初始化方法名称
- **destroy-method**：指定类中销毁方法名称

##### 4、Bean实例化三种方式

- 无参**构造**方法实例化//重点
- 工厂**静态**方法实例化
- 工厂**实例**方法实例化

##### 5、Bean的依赖注入分析

![62728358853](D:\学习资料\高级篇\Bean的依赖注入分析.png)

##### 6、Bean的依赖注入概念

- 依赖注入(**Dependency Injection**):它是Spring框架核心IOC的具体实现
- 在编写程序时，通过控制反转，把对象的创建交给了Spring,但是代码中不可能出现没有依赖的情况。IOC解耦只是降低他们的依赖关系，但不会消除。例如：业务层仍会调用持久层的方法
- 那这种业务层和持久层的依赖关系，在使用Spring之后，就让Spring来维护了。简单的说，就是坐等框架把持久层对象传入业务层，而不用我们自己获取。

##### 7、Bean的依赖注入方式

- **构造方法**

- **set方法**

  - P命名空间注入本质也是set方法注入，但比起上述的set方法注入更加方便，主要体现在配置文件中，如下：

  - 首先，需要引入P命名空间

    ~~~java
    xmlns:p="http://www.springframework.org/schema/p"
    ~~~

  - 其次，需要修改注入方式

    ~~~java
    <bean id="userService" class="com.itheima.service.impl.UserServiceImpl" p:userDao-ref="userDao"></bean>
    ~~~

    ​

##### 8、Bean的依赖注入的数据类型行

- 上面的操作，都是注入的引用Bean，除了对象的引用，普通数据类型，集合等都可以在容器中进行注入
- 注入数据的三种数据类型
  - **普通数据类型**
  - **引用数据类型**
  - **集合数据类型**

##### 9、引入其他配置文件

~~~java
    <import resource="applicationContext-product.xml"/>
    <import resource="applicationContext-user.xml"/>
~~~

##### 10、Spring的重点配置

~~~xml
<bean>标签
    id属性:在容器中Bean实例的唯一标识，不允许重复
    class属性:要实例化的Bean的全限定名
    scope属性：Bean的作用范围，常用是Singleton(默认)和prototype
    <property>标签：属性注入
        name属性:属性名称
        value属性:注入的普通属性值
        ref属性：注入的对象引用值
        <list>标签</list>
        <map>标签</map>
        <properties>标签</properties>
       <constructor-arg>标签
        <import></import>标签:导入其他的Spring的分文件
~~~

#### Spring相关API

##### 1、ApplicationContext的继承体系

- **applicationContext**:接口类型，代表应用上下文，可以通过其实例获得Spring容器中的Bean对象

##### 2、ApplicationContext的实现类

- **ClassPathXmlApplicationContext**

  它是从类的根路径下加载配置文件//推荐使用这种

- **FileSystemXmlApplicationContext**

  它是从磁盘路径上加载配置文件，配置文件可以在磁盘的任意位置

- **AnnotationConfigApplicationContext**

  当使用注解配置容器对象时，需要使用此类来创建spring容器。它用来读取注解

##### 4、Spring的重点API

~~~java
ApplicationContext app = new ClasspathXmlApplicationContext("xml文件");
app.getBean("id");
app.getBean(class);
~~~

#### Spring配置数据源

##### 1、数据源(连接池)的作用

- 数据源是提高程序性能如出现的
- 事先实例化数据源，初始化部分连接资源
- 使用连接资源时从数据源中获取
- 使用完毕后将连接资源归还给数据源
- 常见的数据源(连接池)：**DBCP、C3P0、BoneCP、Druid**等

##### 2、数据源开发的步骤

- 1、导入数据源的坐标和数据库驱动坐标

  2、创建数据源对象

  3、设置数据源的基本数据连接

  4、使用数据源获取连接资源和归还连接资源

##### 3、Spring配置数据源

- 可以将DataSource的创建权交由Spring容器去完成

##### 4、抽取jdbc配置文件

- applicationContext.xml加载jdbc.properties配置文件获得连接信息

- 首先，需要引入context命名空间和约束路径：

  - 命名空间：xmlns:context="http://www.springframework.org/schema/context"

  - 约束路径：http://www.springframework.org/schema/context 

    ​                  http://www.springframework.org/schema/context/spring-context.xsd

    ~~~xml
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
            <property name="driverClass" value="${jdbc.driver}"></property>
            <property name="jdbcUrl" value="${jdbc.url}"></property>
            <property name="user" value="${jdbc.username}"></property>
            <property name="password" value="${jdbc.password}"></property>
        </bean>
    ~~~

    ​

##### 5、知识要点

- String容器加载properties文件

  ~~~xml
  <context:property-placeholder location="classpath:jdbc.properties"/>
  <property name="" value="${key}"/>
  ~~~

#### Spring注解开发

##### 1、Spring原始注解

- Spring是轻代码而重配置的框架，配置比较繁重，影响开发效率，所以注解开发是一种趋势，注解代替xml配置文件可以简化配置，提高开发效率。

- Spring原始注解主要是替代<Bean>的配置

  ![62735820567](D:\学习资料\高级篇\Spring原始注解.png)

- **注意**

  - 使用注解进行开发时，需要在applicationContext.xml中配置组件进行扫描，作用是指定哪个包及其子包下的Bean需要进行扫描以便识别使用注解配置的类、字段和方法。

    ~~~xml
        <!--配置组建扫描-->
        <context:component-scan base-package="com.itheima"/>
    ~~~

##### 2、Spring新注解

![62736534764](D:\学习资料\高级篇\Spring新注解.png)

#### Spring集成Junit

##### 1、Spring集成Junit步骤

- 1、导入spring集成Junit的坐标

  2、使用@Runwith注解替换原来的运行期

  3、使用@ContextConfiguration指定配置文件或配置类

  4、使用@Autowired注入需要测试的对象

  5、创建测试方法进行测试

### Spring集成web环境

#### Spring集成web环境

##### 1、ApplicationContext应用上下文获取方式

- 应用上下文对象是通过**new ClasspathXmlApplicationContext(Spring配置文件)**方式获取的，但是每次从容器中获得Bean时都要编写**new ClasspathXmlApplicationContext(spring配置文件)**，这样的弊端是配置文件加载多次，应用上下文对象创建多次。
- 在Web项目中，可以使用**ServletContextListener**监听Web应用的启动，我们可以在Web应用启动时，就加载Spring的配置文件，创建应用上下文对象**ApplicationContext**,将其存储到最大的域**servletContext**域中，这样就可以在任意位置从域中获得应用上下文**ApplicationContext**对象了

##### 2、Spring提取获取应用上下文的工具

- 上面的分析不用手动实现，Spring提供了一个监听器**ContextLoaderListener**就是对上述功能的封装，该监听器内部加载Spring配置文件，创建应用上下文对象，并存储到**ServletContext**域中，提供了一个客户端工具**WebApplicationContextUtils**供使用者获得应用上下文对象
- 所以我们需要做的只用两件事：
  - 在web.xml中配置**ContextLoaderListener**监听器(导入spring-web坐标)
  - 使用**WebApplicationContextUtils**获得应用上下文对象**ApplicationContext**