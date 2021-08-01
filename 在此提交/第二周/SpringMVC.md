##  SpringMVC

### SpringMVC入门

#### 1.SpringMVC概述

**SpringMVC**是一种基于Java的实现**MVC设计模型**的请求驱动类型的轻量级Web框架，属于**SpringFramWork**的后续产品，已经融合在Spring Web Flow中

- SpringMVC支持**RESTful**编程风格的请求

- 需求：客户端发起请求，服务端接受请求，执行逻辑并进行视图跳转

- 开发步骤

  - 1、导入SpringMVC相关坐标

    2、配置SpringMVC核心控制器DispathcerServlet

    3、创建Controller类和视图页面

    4、使用注解配置Controller类中业务方法的映射地址

    5、配置SpringMVC核心文件spring-mvc.xml

    6、客户端发起请求测试

  ![62744915665](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/SpringMVC.png)


![62745381415](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/SpringMVC%E4%BB%A3%E7%A0%81%E5%AE%9E%E7%8E%B0.png)

![62745386963](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/SpringMVC%E6%B5%81%E7%A8%8B.png)

#### 2、SpringMVC的执行流程

- 1、用户发送请求至前端控制器DispatcherServlet

  2、DspatcherServlet收到请求调用HandlerMapping处理器映射器

  3、处理器映射器找到具体的处理器(可以根据xml配置、注解进行查找)，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DspatcherServlet

  4、DispatcherServlet调用HandlerAdapter处理器适配器

  5、HandlerAdapter经过适配调用具体的处理器(Controller,也叫后端控制器)

  6、Controller执行完成返回ModelAndView

  7、HandlerAdapter将controller执行结果ModelAndView返回给DspatcherServlet

  8、DispatcherServlet将ModelAndView传给ViewReslover视图解析器

  9、ViewReslover解析后返回具体View

  10、DispatcherServlet根据VIew进行渲染视图(即将模型数据填充至视图中)。DispatcherServlet响应用户。

![62745657830](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/SpringMVC%E6%89%A7%E8%A1%8C%E6%B5%81%E7%A8%8B.png)

#### 3、SpringMVC注解解析

- @**RequestMapping**
  - 作用：用于建立请求URL和处理请求方法之间的对应关系
  - 位置：
    - 类上，请求URL的第一级访问目录，此处不写的话，就相当于应用的根目录
    - 方法上，请求URL的第二级访问目录，与类上的使用@RequestMapping标注的一级目录一起组成访问虚拟路径
    - **value**:用于指定请求的URL，它和path属性的作用是一样的
    - **method**：用于指定请求的方式
    - **params**：用于指定限制请求参数的条件。它支持简单的表达式。要求请求参数的key和value必须和配置的一模一样

#### 4、SpringMVC的XML配置解析

##### 1、视图解析器

- SpringMVC有默认组件配置，默认组件都是**DispatcherServlet.properties**配置文件中配置的，该配置文件地址**org/springframwork/web/servlet/DispatcherServlet.properties**,该文件中配置了默认的视图解析器

#### 5、知识要点

##### 1、SpringMVC的相关组件

- 前端控制器:DispatcherServlet
- 处理器映射器:HandlerMapping
- 处理器适配器:HandlerAdapter
- 处理器:Handler
- 视图解析器:View Resolver
- 视图:View

##### 2、SpringMVC的注解和配置

- 请求映射注解:@RequestMapping
- 视图解析器配置:

~~~java
REDIRECT_URL_PREFIX = "redirect:"
FORWARD_URL_PREFIX = "forward:"
    prefix = "";
    suffix = "";
~~~

#### 6、SpringMVC的数据响应方式

##### 1、页面跳转

- 直接返回字符串

- 通过ModelAmdView对象返回

##### 2、回写数据

- 直接返回字符串

  - 将需要回写的字符串直接返回，但此时需要通过**@ResponseBody**注解告知SpringMVC框架，方法返回的字符串不是跳转是直接在http响应体中返回

    ~~~java
        @RequestMapping(value = "/quick7")
        @ResponseBody
        public String save7()throws Exception{
            return "hello ii";
        }
    ~~~

    ​

- 返回对象或集合

  - 在方法上添加@ResponseBody就可以返回json格式的字符串，但是这样配置比较麻烦，配置的代码比较多，因此我们可以使用mvc的注解驱动代替上述配置。

    ~~~xml
        <!--mvc的注解驱动-->
        <mvc:annotation-driven/>
    ~~~

  - 在SpringMVC的各个组件中，**处理器映射器、处理器适配器、视图解析器**称为SpringMVC的三大组件。

  - 使用<mvc:annotation-driven>自动加载RequestMapppingHandlerMapping(处理器映射器)和RequestMappingHandlerAdapter(处理器适配器)，可用在Spring-xml.xml配置文件中使用<mvc:annotation-driven替代注解处理器和适配器的配置

  - 同时使用<mvc:annotation-driven>默认底层就会集成jackson进行对象或集合的json格式字符串的转换

#### 7、Spring获得请求数据

##### 1、获得请求参数

- 客户端请求参数的格式是：**name=value&name=value......**

- 服务器端要获得请求的参数，有时还需要进行数据的封装，SpringMVC可以接收如下类型的参数：

  - 基本类型参数

  ~~~java
      @RequestMapping(value = "/quick11")
      @ResponseBody
      public void save11(String username,int age)throws Exception{
          System.out.println(username);
          System.out.println(age);
      }
  ~~~

  - POJO类型参数

  ~~~java
      @RequestMapping(value = "/quick12")
      @ResponseBody
      public void save12(User user)throws Exception{
          System.out.println(user);
      }
  ~~~

  - 数组类型参数

    ~~~java
        @RequestMapping(value = "/quick13")
        @ResponseBody
        public void save12(String[] strs)throws Exception{
            System.out.println(Arrays.asList(strs)); 
        }
    ~~~

  - 集合类型参数


##### 2、开放静态资源

~~~xml
<!--开放资源的访问，一般为静态资源-->
    <!--<mvc:resources mapping="/js/**" location="/js/"/>-->
    <!--<mvc:resources mapping="/img/**" location="/img/"/>-->

    <mvc:default-servlet-handler/>
~~~

##### 3、请求数据乱码问题

- 当post请求时，数据会出现乱码，我们可以设置一个过滤器来进行编码的过滤。

~~~xml
  <!--配置全局过滤的fileter-->
<filter>
  <filter-name>CharacterEncodingFilter</filter-name>
  <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
  <init-param>
    <param-name>encoding</param-name>
    <param-value>UTF-8</param-value>
  </init-param>
</filter>
  <filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
~~~

##### 4、参数绑定注解@requestParam

当请求的参数名称与Controller的业务方法参数名称不一致时，就需要通过@RequestParam注解显示的绑定

- **value**:与请求参数名称
- **required**:此在指定的请求参数是否必须包括，默认是true，提交时如果没有此参数则报错
- **defaultValue**：当没有指定请求参数时，则使用指定的默认值赋值

~~~java
    @RequestMapping(value = "/quick14")
    @ResponseBody
    public void save14(@RequsetParam(value="name",required=false,defaultValue="itcast") VO vo)throws Exception{
        System.out.println(vo);
    }

~~~

##### 5、获得Restful风格的参数

- **Restful**是一件软件**架构风格**、**设计风格**，而不是标准，只是提供了一组设计原则和约束条件。主要用于客户端和服务器交互类的软件，基于这个风格设计的软件可以更简介，更有层次，更易于实现缓存机制等
- **Restful**风格的请求是使用"**url+请求方式**"表示一次请求目的。HTTP协议里面四个表示操作方式的动词如下：
  - GET:用于获取资源
  - POST:用于新建资源
  - PUT:用于更新资源
  - DELETE:用于删除资源

![62755907285](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E8%8E%B7%E5%BE%97Restful%E9%A3%8E%E6%A0%BC%E7%9A%84%E5%8F%82%E6%95%B0.png)

##### 6、自定义类型转换器

- SpringMVC默认已经提供了一些常用的类转换器，例如客户端提交的字符串转换成int型进行参数设置

- 但是不是所有的数据类型都提供了转换器，没有提供的就需要自定义转换器，例如：日期类型的数据就需要自定义转换器

- 自定义类型转换器的开发步骤：

  - 1、定义转换器类实现Converter接口

    2、在配置文件中声明转换器

    3、在<annotation-driven>中引用转换器

~~~xml
   <!--mvc的注解驱动-->
    <mvc:annotation-driven conversion-service="conversionService"/>
    <!--声明转换器-->
    <bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
        <property name="converters">
            <list></list>
        </property>
    </bean>
~~~

##### 7、获得Servlet相关API

- SpringMVC支持使用原始ServletAPI对象作为控制器方法的参数进行注入，常用的对象如下：

  - HttpServletRequest
  - HttpServletResponse
  - HttpSession

  ~~~java
      @RequestMapping(value = "/quick15")
      @ResponseBody
      public void save15(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
          System.out.println(request);
          System.out.println(response);
          System.out.println(session);
      }
  ~~~

##### 8、获得请求头

- **@RequestHeader**

  - 使用@RequestHeader可以获得请求头信息，相当于web阶段学习的request.getHeader(name)@RequestHeader注解的属性如下:

    - **value**:请求头的名称
    - **require**：是否必须携带此请求头

    ~~~java
        @RequestMapping(value = "/quick15")
        @ResponseBody
        public void save15(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
            System.out.println(request);
            System.out.println(response);
            System.out.println(session);
        }
    ~~~

- **@CookieValue**

  - 使用@CookieValue可以获得指定Cookie的值

  - @CookieValue注解的属性如下：

    - **value**:指定cookie的名称
    - **required**:是否必须携带此cookie

    ~~~java
        @RequestMapping(value = "/quick17")
        @ResponseBody
        public void save17(@CookieValue(value = "JESSIONID",required = false) String jsessionId)throws Exception{
            System.out.println(jsessionId);
        }
    ~~~

##### 9、文件上传

- 文件上传客户端三要素

  - 表单项type="file"
  - 表单的提交方式是post
  - 表单的enctype属性是多部分表单形式，即enctype="multipart/form-data

  ~~~jsp
  <form action="${pageContext.request.contextPath}/xxx/quick18"method="post"enctype="multipart/form-data">
      名称<input type="text" name="username"><br/>
      文件<input type="file" name="upload"><br/>
      <input type="submit" value="upload">
  </form>
  ~~~

- 文件上传原理

  - 当form表单修改为多部分表单时，request.getParameter()将失效
  - enctype="application/x-www-form-urlencoded"时，form表单的正文内容格式是:**key=value&key=value&key=value**
  - 当form表单的enctype取值为Mutilpart/form-data时，请求正文内容就变成多部分形式

![62761319374](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0%E5%8E%9F%E7%90%86.png)

- 单文件上传步骤

  - 1、导入fileupload和io坐标

    ~~~xml
            <dependency>
                <groupId>commons-fileupload</groupId>
                <artifactId>commons-fileupload</artifactId>
                <version>1.3.1</version>
            </dependency>
            <dependency>
                <groupId>commons-io</groupId>
                <artifactId>commons-io</artifactId>
                <version>2.3</version>
            </dependency>
    ~~~

    2、配置文件上传解析器

    ~~~xml
        <!--配置文件上传解析器-->
        <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
            <property name="defaultEncoding" value="UTF-8"></property>
            <property name="maxUploadSize" value="500000"/>
        </bean>
    ~~~

    3、编写文件上传代码

    ~~~java
        @RequestMapping(value = "/quick18")
        @ResponseBody
        public void save18(String username, MultipartFile uploadFile)throws Exception{
            System.out.println(username);
            System.out.println(uploadFile);
        }
    ~~~

    ~~~java
        @RequestMapping(value = "/quick18")
        @ResponseBody
        public void save18(String username, MultipartFile uploadFile)throws Exception{
            System.out.println(username);
            //获得上传文件的名称
            String originalFilename = uploadFile.getOriginalFilename();
            uploadFile.transferTo(new File("C:\\upload\\"+originalFilename));
        }
    ~~~

- 多文件上传实现

~~~java
    @RequestMapping(value = "/quick19")
    @ResponseBody
    public void save19(String username, MultipartFile[] uploadFile)throws Exception{
        System.out.println(username);
        //获得上传文件的名称
        for(MultipartFile multipartFile:uploadFile){
            String originalFilename = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File("C:\\upload\\"+originalFilename));
        }
    }
~~~

~~~xml
<form action="${pageContext.request.contextPath}/xxx/quick19"method="post"enctype="multipart/form-data">
    名称<input type="text" name="username"><br/>
    文件<input type="file" name="uploadFile"><br/>
    文件<input type="file" name="uploadFile"><br/>
    <input type="submit" value="提交">
</form>
~~~
#### 8、SpringMVC拦截器

##### 1、拦截器(interceptor)的作用

Spring MVC的**拦截器**类似于Servlet开发中的过滤器Filter,用于对处理器进行**预处理**和**后处理**

将拦截器按一定的顺组联结成一条链，这条链称为拦截器链(Interceptor Chain)。在访问被拦截的方法或字段时，拦截器链中的拦截器就会按其之前定义的顺序被调用。拦截器也是AOP思想的具体实现

##### 2、拦截器和过滤器的区别

![62763639177](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E6%8B%A6%E6%88%AA%E5%99%A8%E5%92%8C%E8%BF%87%E6%BB%A4%E5%99%A8%E7%9A%84%E5%8C%BA%E5%88%AB.png)

##### 3、拦截器快速入门

自定义拦截器很简单，只有如下三步：

- 创建拦截类实现HandlerInterceptor接口

~~~java
package com.itheima.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor1 implements HandlerInterceptor {
    //在目标方法执行之前 执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle....");
        String param = request.getParameter("param");
        if("yes".equals("yes")){
            return true;//返回ture代表放行 false代表不放行
        }
        else {
            request.getRequestDispatcher("/error.jsp").forward(request,response);
            return false;//返回ture代表放行 false代表不放行
        }
    }

    //在目标方法执行之后 视图返回之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("name","itheima");
        System.out.println("preHandle....");
    }

    //在流程都执行完毕后 执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
~~~

- 配置拦截器

~~~xml
    <!--配置拦截器-->
    <mvc:interceptors>
        <mvc:interceptor>
            <!--对哪些资源执行拦截操作-->
            <mvc:mapping path="/**"/>
            <bean class="com.itheima.interceptor.MyInterceptor1"/>
        </mvc:interceptor>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <bean class="com.itheima.interceptor.MyInterceptor2"/>
        </mvc:interceptor>
    </mvc:interceptors>
~~~

- 测试拦截器的拦截结果

##### 4、拦截器方法说明

![62770020566](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E6%8B%A6%E6%88%AA%E6%96%B9%E6%B3%95%E8%AF%B4%E6%98%8E.png)

#### 9、SpringMVC异常处理机制

##### 1、异常处理思路

![62771734685](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E7%9A%84%E6%80%9D%E8%B7%AF.png)

##### 2、异常处理两种方式

- 使用Spring MVC提供的简单异常处理器SimpleMappExceptionResolver

  ![62771801857](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E7%AE%80%E5%8D%95%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86.png)

- 使用Spring的异常处理接口HandlerExceptionResolver自定义自己的异常处理

  - 1、创建异常处理器类实现HandlerExceptionResolver

    2、配置异常处理器

    3、编写异常页面

    4、测试页面跳转

#### 10、Spring的AOP简介

##### 1、什么是AOP

- **AOP**为**A**spect **O**riented **P**rogramming的缩写，意思为**面向切面编程**，是通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。
- AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之问的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

##### 2、AOP的作用及其优势

- 作用:在程序运行期间，在不修改源码的情况下对方法进行功能增强
- 优势:减少重复代码，提高开发效率，并且便于维护

##### 3、AOP的底层实现

- 实际上，AOP的底层是通过Spring提供的的动态代理技术实现的。在运行期间，Spring通过动志代理技术动态的生成代理对象，代理对象方法执行时进行增强寸能的介入，在去调用目标对象的方法，从而完成功能的增强。

##### 4、AOP的动态代理技术

- 常用的动态代理技术

  - JDK代理:基于接口的动态代理技术
  - cglib代理:基于父类的动态代理技术

  ![62772313980](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/AOP%E7%9A%84%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86%E6%8A%80%E6%9C%AF.png)

  ##### 5、JDK的动态处理

~~~java
package com.xxx.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ProxyTest {

    public static void main(String[] args){

        //创建目标对象
        final Target target = new Target();

        //增强对象
        final Advice advice = new Advice();

        //返回值 就是动态生成的代理对象
        TargetInterface proxy=(TargetInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//目标对象类加载器
                target.getClass().getInterfaces(),//目标对象相同的接口字节码对象数组
                new InvocationHandler() {
                    //调用代理对象的任何方法  实质执行的都是invoke方法
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        advice.before();//前置增强
                        Object invoke = method.invoke(target, args);//执行目标方法
                        advice.afterReturning();//后置增强
                        return invoke;
                    }
                }
        );

        //调用代理对象的方法
        proxy.save();
    }
}
~~~

##### 6、cglib的动态代理

##### 7、AOP的相关概念

AOP的常用术语如下：

- Target(目标对象)：代理的目标对象
- Proxy(代理)：一个类被AOP织入增强后，就产生一个结果代理类
- Joinpoint(连接点)：所谓连接点是指那些被拦截到的点。在Spring中，这些点指的是方法，因为spring只支持方法类型的连接点
- Pointcut(切入点)：所谓切入点是指我们要对哪些Joinpoint进行拦截的定义
- Advice(通知/增强)：所谓通知是指拦截到Joinpint之后所要做的事情就是通知
- Aspect(切面)：是切入点和通知(引介)的结合
- Weaving(织入)：是指把增强应用到目标对象来创建新的代理对象的过程。spring采用动态代理织入，而Aspect采用编译期织入和类装载。

##### 8、AOP开发明确的事项

###### 1、需要编写的内容

- 编写核心业务代码(目标类的目标方法)
- 编写切面类，切面类中有通知(增强功能方法)
- 在配置文件中，配置织入关系，即将哪些通知与哪些连接点进行结合

###### 2、AOP技术实现的内容

Spring框架监控切入点方法的执行。一旦监控到切入点方法被运行，使用代理机制，动态创建目标对象的代理对象，根据通知类别，在代理对象的对应位置，将通知对应的功能织入，完成完整的代码逻辑运行

###### 3、AOP底层采用哪种代理方式

在spring中，框架会根据目标类是否实现了接口来决定采用哪种动态代理的方式

#### 11、基于XML的AOP开发

##### 1、快速入门

![62778198943](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E5%9F%BA%E4%BA%8EXML%E7%9A%84AOP%E5%BC%80%E5%8F%91.png)

##### 2、切点表达式的写法

###### 1、切点表达式的写法

- 表达式的语法

  ~~~xml
  execution([修饰符]返回值类型 包名.类名.方法名(参数))
  ~~~

  - 访问修饰符可以省略
  - 返回值类型、包名、类名、方法名可以使用星号*代表任意
  - 包名与类名之间一个点，代表当前包下的类，两个点..表示当前包及其子包下的类、
  - 参数列表可以使用两个点..表示任意个数，任意类型的参数列表

![62779101289](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E5%88%87%E7%82%B9%E8%A1%A8%E8%BE%BE%E5%BC%8F%E7%9A%84%E5%86%99%E6%B3%95.png)

##### 3、通知的类型

![62779126447](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E9%80%9A%E7%9F%A5%E7%9A%84%E9%85%8D%E7%BD%AE%E8%AF%AD%E6%B3%95.png)

##### 4、切点表达式的抽取

![62779329883](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/aop%E9%85%8D%E7%BD%AE.png)

#### 12、基于注解的AOP开发

##### 1、快速入门

![62779364730](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E5%9F%BA%E4%BA%8E%E6%B3%A8%E8%A7%A3%E7%9A%84AOP%E5%BC%80%E5%8F%91.png)

##### 2、注解配置AOP详解

###### 1、注解统治的类型

###### ![62780390047](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E6%B3%A8%E8%A7%A3%E9%85%8D%E7%BD%AEAOP%E8%AF%A6%E8%A7%A3.png)

###### 2、切点表达式的抽取

![62781072451](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E5%88%87%E7%82%B9%E8%A1%A8%E8%BE%BE%E5%BC%8F%E7%9A%84%E6%8A%BD%E5%8F%96.png)

#### 13、Spring的事务控制

##### 1、编程式事务控制相关对象（了解

![62781187045](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/PlatformTransaction.png)

![62781198618](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/TransactionDefinition.png)

![62781233727](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E4%BA%8B%E5%8A%A1%E4%BC%A0%E6%92%AD%E8%A1%8C%E4%B8%BA.png)

![62781287796](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/TransactionStatus.png)

##### 2、基于XML的声明式事务控制

###### 1、什么是声明式事务控制

Spring的声明式事务顾名思义就是**采用声明的方式来处理事务**。这里所说的声明，就是指在配置文件中声明，用在Spring配置文件中声明式的处理事务来代替代码式的处理事务。

- 声明式事务处理的作用
  - 事务管理不侵入开发的组件。具体来说，业务逻辑对象就不会意识到正在事务管理之中，事实上也应该如此，因为事务管理是属于系统层面的服务，而不是业务逻辑的一部分，如果想要改变事务管理策划的话，也只需要在定义文件中重新配置即可
  - 在不需要事务管理的时候，只要在设定文件上修改一下，即可移去事务管理服务，无霜改变代码重新编译这样维护起来极其方便
  - **注意：Spring声明式事务控制底层就是AOP**

###### 2、声明式事务控制的实现

声明式事务控制明确事项：

- 谁是切点?
- 谁是通知?
- 配置切面?
- 切点发发的事务参数的配置

![62781690664](https://github.com/2372607196/NMID_2/blob/main/%E5%9C%A8%E6%AD%A4%E6%8F%90%E4%BA%A4/%E7%AC%AC%E4%BA%8C%E5%91%A8/screen%E2%80%94%E2%80%94shoot/%E5%88%87%E7%82%B9%E6%96%B9%E6%B3%95%E7%9A%84%E4%BA%8B%E5%8A%A1%E5%8F%82%E6%95%B0%E7%9A%84%E9%85%8D%E7%BD%AE.png)

###### 3、基于注解的声明式事务控制

- 注解配置声明式事务控制解析
  - 使用@Transactional在需要进行事务控制的类或是方法上修饰，注解可用的属性同xml配置方式，例如隔离级别、传播行为等。
  - 注解使用在类上，那么该类下的所有方法都使用同一套注解参数配置。
  - 使用在方法上，不同的方法可以采用不同的事务参数配置。
  - Xml配置文件中要开启事务的注解驱动<tx : annotation-driven / >
