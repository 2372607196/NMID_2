## Spring Boot

### 1、SpringBoot 概述

SpringBoot提供了一种快速使用Spring的方式，基于约定优于配置的思想，可以让开发人员不必在配置与逻辑业务之间进行思维的切换，全身心的投入到逻辑业务的代码编写中，从而大大提高了开发的效率，一定程度上缩短了项目周期。2014年4月，Spring Boot 1.0.0发布。Spring的顶级项目之一(https://spring.io)。

#### 2、SpringBoot功能

##### 1、自动配置

Spring Boot的自动配置是一个运行时(更准确地说，是应用程序启动时）的过程，考虑了众多因素，才决定Spring配置应该用哪个，不该用哪个。该过程是SpringBoot自动完成的。

##### 2、启动依赖

起步依赖本质上是一个Maven项目对象模型(Project Object Model，POM)，定义了对其他库的**传递依赖**，这些东西加在一起即支持某项功能。
简单的说，起步依赖就是将具备某种功能的坐标打包到一起，并提供一些默认的功能。

##### 3、辅助功能

提供了一些大型项目中常见的非功能性特性，如嵌入式服务器、安全、指标，健康检测、外部配置等。

**Spring Boot并不是对Spring功能上的增强，而是提供了一种快速使用Spring的方式。**

### 2、SpringBoot 快速入门

#### 1、Maven实现SpringBoot

实验步骤：

- 创建Maven项目
- 导入SpringBoot起步依赖
- 定义Controller
- 编写引导类
- 启动测试

#### 2、快速构建SpringBoot

### 3、SpringBoot 起步依赖原理分析

- 在spring-boot-starter-parent中定义了各种技术的版本信息，组合了一套最优搭配的技术版本。
- 在各种starter中，定义了完成该功能需要的坐标合集，其中大部分版本信息来自于父工程。
- 我们的工程继承parent，引入starter后，通过依赖传递，就可以简单方便获得需要的jar包，并且不会存在版本冲突等问题。

### 4、SpringBoot配置

#### 配置文件分类

SpringBoot是基于约定的，所以很多配置都有默认值，但如果想使用自己的配置替换默认配置的话，就可以使用application.properties或者application.yml (application.yaml)进行配置。

- properties:

  ~~~properties
  server.port=8080
  ~~~

- yml:

  ~~~yaml
  server:
  	port: 8080
  ~~~

- SpringBoot提供了2种配置文件类型:properteis和yml/yaml

- 默认配置文件名称: application

- 在同一级目录下优先级为:properties > yml > yaml

#### YAML

YAML全称是YAMLAin't Markup Language。YAML是一种直观的能够被电脑识别的的数据数据序列化格式，并且容易被人类阅读，容易和脚本语言交互的，可以被支持YAML库的不同的编程语言程序导入，比如: C/C+ + , Ruby, Python, Java, Perl, C#, PHP等。YML文件是以数据为核心的，比传统的xml方式更加简洁。
YAML文件的扩展名可以使用.yml或者.yaml。

- yml:

  ~~~yaml
  server:
  	port: 8080
  	address: 127.0.0.1
  ~~~

##### YAML:基本语法

- 大小写敏感
- 数据值前边必须有空格。作为分隔符
- 使用缩进表示层级关系
- 缩进时不允许使用Tab键，只允许使用空格(各个系统Tab对应的空格数目可能不同，导致层次混乱)。
- 缩进的空格数目不重要，只要相同层级的元素左侧对齐即可
- （#表示注释，从这个字符一直到行尾。都会被解析器忽略。

##### YAML:数据格式

- 对象(map):键值对的集合

  ~~~yaml
  person:
  	name: zhangsan
  #行内写法
  person:{name:zhangsan}
  ~~~

- 数组：一组按次序排列的值

~~~yaml
address:
	- beijing
	- shanghai
#行内写法
address:[beijing,shanghai]
~~~

- 纯量: 单个的、不可再分的值

  ~~~yaml
  msg1:'hello \n world' #单引忽略转移字符
  msg2:"hello \n world" #双引识别转移字符
  ~~~

##### YAML:参数引用

~~~yaml
name: lisi

person:
	name: ${name} # 引用上边定义的name值
~~~

#### 读取配置文件内容

##### 读取配置内容

- @Value
- Environment
- @ConfigurationProperties

#### profile

我们在开发Spring Boot应用时，通常同一套程序会被安装到不同环境，比如:开发、测试、生产等。其中数据库地址、服务器端口等等配置都不同，如果每次打包时，都要修改配置文件，那么非常麻烦。profile功能就是来进行动态配置切换的。

1) profile配置方式

- 多profile文件方式
- yml多文档方式

2) profile激活芳式

- 配置文件
- 虚拟机参数
- 命令行参数

1) profile是用来完成不同环境下，配置动态切换功能的。

2) profile配置方式

- 多profile文件方式:提供多个配置文件，每个代表一种环境。


- application-dev.properties/yml开发环境

- application-test.properties/yml测试环境. 

- application-pro.properties/yml生产环境

- yml多文档方式:
  在yml中使用---分隔不同配置

- 3) profile激活方式

  配置文件:再配置文件中配置: spring.profiles.active=dev
  虚拟机参数:在VM options指定:-Dspring.profiles.active=dev命令行参数: java-jar xxx.jar --spring.profiles.active=dev

#### 内部配置加载顺序

Springboot程序启动时，会从以下位置加较配置文件:

1. file:./config/:当前项目下的/config目录下

2. file:./:当前项目的根目录

3. classpath:/config/: classpath的/config目录

4. classpath:t: classpath的根目录

  加载项字为上文的排列顶序，高优先级配置的属性会生效

#### 外部配置加载顺序

通过官网查看外部属性加载顺序:
https://docs.spring.io/spring-boot/docs/current/reference/htmI/boot-features-external-config.htm

#### SpringBoot整合其他框架

##### SpringBoot 整合Junit

- 搭建SpringBoot工程
- 引入starter-test起步依赖
- 编写测试类
- 添加测试相关注解
  - @RunWith(SpringRunner.class)
  - @SpringBootTest(classes =启动类.class）
  - 编写测试方法

##### SpringBoot 整合redis

- 搭建SpringBoot工程
- 引入redis起步依赖
- 配置redis相关属性
- 注入RedisTemplate模板
- 编写测试方法，测试

##### SpringBoot 整合mybatis

- 搭建SpringBoot工程
- 引入mybatis起步依赖，添加mysql驱动
- 编写DataSource和MyBatis相关配置
- 定义表和实体美
- 编写dao和mapper文件/纯注解开发
- 测试

### 5、Spring Boot自动配置

#### Condition

Condition是在Spring 4.0增加的条件判断功能，通过这个可以功能可以实现选择性的创建Bean操作。

- 自定义条件:

  - 定义条件类:自定义类实现Condition接口，重写matches方法，在matches方法中进行逻辑判断，返回

    boolean值。matches方法两个参数:

    - context: 上下文对象，可以获取属性值，获取类加载器，获取BeanFactory等。
    - metadata:元数据对象。用于获取注解属性。

- 判断条件:在初始化Bean时，使用Rconditional(条件类.class)注解

-  SpringBoot提供的常用条件注解:

  - conditionalonProperty:判断配置文件中暴否有对应属性和值才初始化Bean
  - conditionalonclass:判断环境中是否有对应字节码文件才初始化Bean
  - ConditionalOnMi ssingBean:判断环境中没有对应Bean才初始化Bean

#### 切换内置web服务器

SpringBoot的web环境中默认使用tomcat作为内置服务器，其实SpringBoot提供了4中内置服务器供我们选择，我们可以很方便的进行切换。

~~~xml
     <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <!--排除tomcat依赖-->
                <exclusion>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--引入jetty的依赖-->
        <dependency>
            <artifactId>spring-boot-starter-jetty</artifactId>
            <groupId>org.springframework.boot</groupId>
        </dependency>
~~~

#### @Enable*注解

SpringBoot中提供了很多Enable开头的注解，这些注解都是用于动态启用某些功能的。而其底层原理是使用@Import注解导入一些配置类，实现Bean的动态加载。

#### @Import注解

@Enable*底层依赖于@Import主解导入一些类，使用@Import导入的类会被Spring加载到IOC容器中。而@Import提供4中用法:

- 导入Bean
- 导入配百类
- 导入lmportSelector实现类。
- 一般用于加载配置文件中的类导入ImportBeanDefinitionRegistrar实现类。

#### @EnableAutoConfiguration 注解

- @EnableAutoConfiguration注解内部使用@Import (AutoconfigurationImportselector.class)来加载配置类。
- 配置文件位置:META-INF/spring.factories，该配置文件中定义了大量的配置类，当SpringBoot应用启动时，会自动加载这些配置类，初始化Bean
- 并不是所有的Bean都会被初始化，在配置类中使用Condition来加载满足条件的Bean

#### 自定义starter步骤分析

- 创建redis-spring-boot-autoconfigure模块
- 创建redis-spring-boot-starter模块,依赖redis-spring-boot-autoconfigure的模块
- 在redis-spring-boot-autoconfigure模块中初始化Jedis的Bean。并定义META-INF/spring.factories文件
- 在测试模块中引入自定义的redis-starter依赖，测试获取Jedis 的Bean，操作redis。

#### SpringBoot 监听机制

SpringBoot的监听机制，其实是对Java提供的事件监听机制的封装。

Java中的事件监听机制定义了以下几个角色:

- 事件: Event，继承java.util.EventObject类的对象
- 事件源:Source，任意对象Object
- 监听器空Listener，实现java.util.EventListener接口的对象

SpringBoot在项目启动时，会对几个监听器进行回调，我们可以实现这些监听器接口，在项目启动时完成—些操作。

- ApplicationContextInitializer
- SpringApplicationRunListener
- CommandLineRunner
- ApplicationRunner

#### SpringBoot 监控

SpringBoot自带监控功能Actuator，可以帮助实现对程序内部运行情况监控，比如监控状况、Bean加载情况、配置属性、日志信息等。

导入依赖坐标

~~~xml
<dependency>

       <groupld>org.springframework.boot</groupld>
       <artifactld>spring-boot-starter-actuator</artifactld>
</dependency>

~~~

②访问http://localhost:8080/acruator

#### SpringBoot 监控 - Spring Boot Admin

- Spring Boot Admin是一个开源社区项目，用于管理和监控SpringBoot应用程序。

- Spring Boot Admin有两个角色，客户端(Client)和服务端(Server).

- 应用程序作为SpringBoot Admin Client向为Spring BootAdmin Server注册

- Spring Boot Admin Server的UI界面将Spring Boot Admin Client的Actuator Endpoint上的一些监控信息。

  admin-server:

  创建admin-server模块

  导入依赖坐标admin-starter-server

  在引导类上启用监控功能@EnableAdminServer

  admin-client:
  创建admin-client模块
  导入依赖坐标admin-starter-client
  配置相关信息: server地址等
  启动server和client服务，访问server

#### SpringBoot 项目部署

SpringBoot项目开发完毕后，支持两种方式部署到服务器:

- jar包(官方推荐)
- JAR包(官方推荐)

