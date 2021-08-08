## Mybatis

### 1、Mybatis简介

#### 1、原始jdbc操作的分析

原始jdbc开发存在的问题如下:

- 数据库连接创建、释放频繁造成系统资源浪费从而彤响系统性能
- sql语句在代码中硬编码，造成代码不易维护，实际应用sql变化的可能较大，sql变动需要改变java代码。
- 查询操作时，需要手动将结果集中的数据手动封装到实体中。插入操作时，需要手动将实体的数据设置到sql语句的占位符位置

应对上述问题给出的解决方案:

- 使用数据库连接池初始化连接资源
- 将sql语句抽取到xml配置文件中
- 使用反射、内省等底层支术，自动将实体与表进行属性与字段的自动映射

#### 2、什么是Mybatis

- mybatis是一个优秀的基于java的持久层框架，它内部封装了jdbc，使开发者只需要关注sql语句本身，而不需要花费精力去处理加载驱动、创建连接、创建statement等繁杂的过程。
- mybatis通过xml或注解的方式将要执行的各种statement配置起来，并通过java对象和statement中sql的动态参数进行映射生成最终执行的sql语句。
- 最后mybatis框架执行sq并将结果映射为java对象并返回。采用ORM思想解决了实体和数据库映时的问题，对jdbc进行了封装，屏蔽了jdbc api底层访问细节，使我们不用与jdbc api打交道，就可以完成对数据库的持久化操作。

### 2、Mybatis快速入门

#### 1、MyBatis开发步骤

MyBatis开发步骤:

- 添加MyBatis的坐标

~~~xml
   <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.32</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.6</version>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
~~~

- 创建user数据表
- 编写User实体类
- 编写映射文件UserMapper.xml

~~~xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="userMapper">

    <select id="findAll" resultType="com.xxx.domain.User">
        select * from stu_info;
    </select>

</mapper>
~~~

- 编写核心文件SqlMapConfig.xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--数据源环境-->
    <environments default="developement">
        <environment id="developement">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/students"/>
                <property name="username" value="root"/>
                <property name="password" value="wanghao456.."/>
            </dataSource>
        </environment>
    </environments>

    <!--加载映射文件-->
    <mappers>
        <mapper resource="com/xxx.mapper/UserMapper.xml"></mapper>
    </mappers>
</configuration>
```

- 编写测试类

~~~java
package com.xxx.test;

import com.xxx.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    @Test
    public void test1()throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapconfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session回话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数:namespace+id
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        //打印数据
        System.out.println(userList);
        //释放资源
        sqlSession.close();
    }
}
~~~

### 3、MyBatis的映射文件概述

![62790486693](D:\学习资料\近代史\MyBatis的映射文件概述.png)

### 4、MyBatis的增删改查操作

#### 1、MyBatis的插入数据操作

##### 插入操作注意问题

- 插入语句使用insert标签
- 在映射文件中使用parameterType属性指定要插入的数据类型
- Sql语句中使用#{实体属性名}方式引用实体中的属性值
- 插入操作使用的API是sqlSession.insert(“命名空间.id”,实体对象);
- 插入操作涉及数据库数据变化。所以要使用sqlSession对象显示的提交事务，即sqlSession.commit0

#### 2、MyBatis的修改数据操作

##### 修改操作注意问题

- 修改语句使用update标签
- 修改操作使用的是API是sqlSession.update("命名空间.id",实体对象)

##### 删除操作注意问题

- 删除语句使用delete标签
- Sql语句中使用#{任意字符串}方式引用传递的单个参数
- 删除操作使用的API是sqlSession.delete("命名空间.id",Object);

#### 3、增删改查映射配置与API

![62790993988](D:\学习资料\近代史\增删改查映射配置与API.png)

### 5、MyBatis的核心配置文件

#### 1、MyBatis核心配置文件层级关系

- configuration 配置
  - properties 属性
  - settings 设置
  - typeAliases 类别名
  - typeHandlers 类型处理器
  - objectFactory 对象工厂
  - plugins 插件
  - environments 环境
    - environment 环境变量
      - transactionManager 事务管理器
      - dataSource 数据源
  - databaseIdProvider 数据库厂商标识
  - mappers 映射器

#### 2、MyBatis常用配置解析

##### 1、environments标签

![62791714001](D:\学习资料\近代史\environments标签.png)

其中，事务管理器(transactionManager）类型有两种:

- JDBC:这个配置就是直接使用了JDBC的提交和回滚设置，它依赖于从数据源得到的连接来管理事务作用域。
- MANAGED: 这个配置几乎没做什么。它从来不提交或回滚一个连接，而是让容器来管理事务的整个生命周期(比划如JEE应用服务器的上下文)。默认情况下它会关闭连接，然而一些容器并不希望这样，因此需要将closeConnection 属性设置为false 来阻止它默认的关闭行为。

其中，数据源(dataSource)类型有三种:

- UNPOOLED:这个数据源的实现只是每次被请求时打开和关闭连接。
- POOLED:这种数据源的实现利用“池”的概念将JDBC连接对象组织起来。
- JNDI:这个数据源的实现是为了能在如EJB或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个JNDI上下文的引用。

##### 2、mapper标签

该标签的作用是加载映射的，加载方式有如下几种:

- 使用相对于类路径的资源引用，例如: <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
- 使用完全限定资源定位符(URL)，例如: <mapper url="file:///var/mappers/AuthorMapper.xml"/>
- 使用映射器接口实现类的完全限定类名，例如: <mapper class="org.mybatis.builder.AuthorMapper"/>
- 将包内的映射器接口实现全部主册为映射器，例如: <package name="org.mybatis.builder" />

##### 3、Properties标签

![62791801618](D:\学习资料\近代史\Propert标签.png)

##### 4、typeAliase标签

![62791853388](D:\学习资料\近代史\typeAliases标签.png)

![62791864264](D:\学习资料\近代史\typeAliases标签2.png)

### 6、MyBatis的相应API

#### 1、SqlSession工厂构建器SqlSessionFactoryBuilder

常用API:SqlSessionFactory build(InputStream inputStream)

通过加载mybatis的核心文件的输入流的形式构建一个SqlSessionFactory对象

![62795039838](D:\学习资料\近代史\SqlSessionFactoryBuilder.png)

#### 2、SqlSession工厂对象SqlSessionFactory

SqlSessionFactory有多个方法创建SqlSession实例。常用的有如下两个

![62795052801](D:\学习资料\近代史\SqlSessionFactory.png)

#### 3、SqlSession会话对象

![62795081718](D:\学习资料\近代史\SqlSession会话对象.png)

### 7、mybatis的dao层实现

#### 1、代理开发方式

##### 1、代理开发方式介绍

采用Mybatis的代理开发方式实现DAO层的开发，这种方式是我们后面进入企业的主流。
Mapper接口开发方法只需要程序员编写Mapper接口(相当于Dao接口)，由Mybatis框架根据接口定义创建接口的动态代理对象，代理对象的方法体同上边Dao接口实现类方法。
Mapper接口开发需要遵循以下规范:
1、Mapper.xml文件中的namespace与mapper接口的全限定名相同

2、Mapper接口方法名和Mapper.xml中定义的每个statement的id相同

3、Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql的parameterType的类型相同

4、Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同

##### 2、编写UserMapper接口

![62795645438](D:\学习资料\近代史\编写UserMapper接口.png)

##### 3、测试代理方式

~~~java
@Test
public void testProxyDao() throws lOException {
IlnputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");sqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(resourceAsStreSqlSession sqlSession = sqlSessionFactory.openSession();
//获得MyBatis框架生成的UserMapper接口的实现类
UserMapper userMapper = sqlSession.getMapper(UserMapper.class);User user = userMapper.findByld(1);
System.out.println(user);
sqlSession.close();
}
~~~

### 8、MyBatis映射文件深入

#### 1、动态sql语句

##### 1、动态SQL之<if>

我们根据实体类的不同取值，使不同的SQL语句来进行查询。比如在id如果不为空时可以根据id查询，如果username不同空时还要加入用户名作为条件。这种情况在我们的多条件组合查询中经常会碰到。

~~~xml
    <select id="findByCondition" parameterType="user" resultType="user">
        select * from user
        <where>
        <if test="id!=0">
            and id=#{id}
        </if>
        <if test="username!=null">
            and username=#{username}
        </if>
        <if test="password!=null">
            and password=#{password}
        </if>
        </where>
    </select>
~~~

##### 2、动态SQL之<foreach

循环执行sql的拼接操作,例如:SELECT * FROM USER WHERE id IN (1,2,5)

~~~xml
    <select id="findByIds" parameterType="list" resultType="user">
        select * from stu_info
        <where>
            <foreach collection="list" open="id in(" close=")" item="id" separator=",">
                #{id}
            </foreach>
        </where>
    </select>
~~~

#### 2、SQL片段抽取

Sql中可将重复的sql提取出来，使用时用include引用即可，最终达到sql重用的目的

~~~xml
    <!--sql语句的抽取-->
    <sql id="selectUser">
        select * from user
    </sql>
~~~

#### 3、MyBatis映射文件配置：

<select:查询

<insert:插入

<update:修改

<delete:删除

<where:where条件

<if:if判断

<foreach:循环

<sql:sql片段抽取

### 9、mybatis核心配置文件深入

#### 1、typeHandlers标签

无论是MyBatis在预处理语句(PreparedStarement)中设置一个参数时，还是从结果集中取出一个值时，都会用类处理器将获取的值以合适的方式转换为Java类型。下面表述了一些默认的类型处理器(截取部分)

![62796722113](D:\学习资料\近代史\typeHandlers标签.png)

你可以重写类型处理器或创建你自己的类型处理器来处理不支持的或非标准的类型。具体做法为:实现
org.apache.ibatis.type.TypeHandler接口，或继承一个很便利的类org.apache.ibatis.type.BaseTypeHandler，然后可以选择性地将它映射到一个JDBC类型。例如需求:一个Java中的Date数据类型，我想将之存到数据库的时候存成一个1970年至今的亳秒数，取出来时转换成java的Date，即java的Date与数据库的varchar毫秒值之间转换。
开发步骤:

- 定义转换类继承类BaseTypeHandler<T>
- 覆盖4个未实现的方法，其中setNonNullParameter为java程序设置数据到数据库的回调方法，getNullableResult为查询时mysql的字符串类型转换成java的Type类型的方法
- 在MyBatis核心配置文件中进行注册
- 测试转换是否正确

#### 2、plugins标签

MyBatis可以使用第三方的插件来对功能进行扩展，分页助手PageHelper是将分页的复杂操作进行封装，使用简单的方式即可获得分页的相关数据
开发步骤:

- 导入通用PageHelper的坐标

~~~xml
        <dependency>
            <groupId>com.github.jsqlparser</groupId>
            <artifactId>jsqlparser</artifactId>
            <version>2.0</version>
        </dependency>
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>5.1.10</version>
        </dependency>
~~~

- 在mybatis核心配置文件中配置PageHelper插件

~~~xml
    <!--配置分页助手插件-->
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageInterceptor">
        </plugin>
    </plugins>
~~~

- 测试分页数据获取

~~~java
    @Test
    public void test3()throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //设置分页相关参数 当前页 +每页显示的条
        PageHelper.startPage(2,3);

        List<User> userList = mapper.findAll();
        for (User user:userList
             ) {
            System.out.println(user);
        }

        //获得与分页相关参数
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        System.out.println("当前页:"+pageInfo.getPageNum());
        System.out.println("每页显示条数"+pageInfo.getPageSize());
        System.out.println("总条数:"+pageInfo.getTotal());
        System.out.println("总页数:"+pageInfo.getPages());
        System.out.println("上一页:"+pageInfo.getPrePage());
        System.out.println("下一页:"+pageInfo.getNextPage());
        System.out.println("是否是第一页："+pageInfo.isIsFirstPage());
        System.out.println("是否是最后一页:"+pageInfo.isIsLastPage());

        sqlSession.close();
    }
~~~

#### 3、MyBatis核心配置文件常用标签:

1、properties标签:该标签可以加载外部的properties文件

2、typeAliases标签:设置类别名

3、environments标签:数据源环境配置标签

4、typeHandlers标签:配置自定义类型处理器

5、plugins标签:配置MyBatis的插件

### 9、MyBatis的多表操作

#### 1、一对一查询

userMapper.xml

~~~xml
    <resultMap id="userMap" type="user">
        <id column="uid" property="id"></id>
        <result column="username" property="username"></result>
        <result column="password" property="password"></result>
        <result column="birthday" property="birthday"></result>
        <!--配置集合信息
        property:集合名称
        ofType:当前集合中的数据类型
        -->
        <collection property="orderList" ofType="order">
            <!--封装order的数据-->
            <id column="oid" property="id"></id>
            <result column="ordertime" property="ordertime"></result>
            <result column="total" property="total"></result>
        </collection>
    </resultMap>

    <select id="findAll" resultMap="userMap">
        select *,o,id from user u,order o where u.id=o.id
    </select>
~~~

orderMapper.xml

~~~xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.xxx.mapper.OrderMapper">

    <resultMap id="orderMap" type="order">
        <!--手动指定字段与实体属性的映射关系
        column:数据表的字段名称
        property:实体的属性名称
        -->
        <id column="oid" property="id"></id>
        <result column="ordertime" property="ordertime"></result>
        <result column="total" property="total"></result>
        <!--<result column="uid" property="user.id"></result>-->
        <!--<result column="username" property="user.username"></result>-->
        <!--<result column="password" property="user.password"></result>-->
        <!--<result column="birthday" property="user.birthday"></result>-->

        <!--
        property:当前实体(order)中的属性名称(private User user)
        javaType:当前实体(order)中的属性的类型(User)
        -->
        <association property="user" javaType="user">
            <id column="uid" property="id"></id>
            <result column="username" property="username"></result>
        </association>
    </resultMap>
~~~

#### 2、多对多查询

userMapper.xml

~~~xml

    <resultMap id="userRoleMap" type="user">
        <!--user的信息-->
        <id column="userId" property="id"></id>
        <result column="username" property="username"></result>
        <result column="password" property="password"></result>
        <result column="birthday" property="birthday"></result>
        <!--user内部的roleList信息-->
        <collection property="roleList" ofType="role">
            <id column="roleId" property="id"></id>
            <result column="roleName" property="roleName"></result>
            <result column="roleDesc" property="roleDesc"></result>
        </collection>
    </resultMap>

    <select id="findUserAndRoleAll" resultMap="userRoleMap">
        select * from user u,sys_user_role ur, sys_role r where u.id=ur.userId and ur.roleId=r.id
    </select>
~~~

#### 3、MyBatis多表配置方式

一对一配置:使用<resultMap>做配置

一对多配置:使用<resultMap>+<collection>做配置

多对多配置:使用<resultMap>+<collection>做配置

### 10、MyBatis的注解开发

#### 1、MyBatis的常用注解

这几年来注解开发越来越流行，Mybatis也可以使用注解开发方式，这样我们就可以减少编写Mapper映射文件了。我们先围绕一些基本的CRUD来学习，再学习复杂映射多表操作。

- @lnsert:实现新增
- @Update:实现更新
- @Delete:实现删除
- @Select:实现查询
- @Result:实现结果集封装
- @Results:可以与@Result一起使用，封装多个结果集
- @One:实现一对一结果集封装
- @Many:实现一对多结果集封装
- sqlMapConfig.xml

~~~xml
<!--加载映射关系-->
<mappers>
     <!--指定接口所在的包-->
<package name="com.itheima. mapper"</package></mappers>
~~~

- UserMapper.interface

~~~java
public interface UserMapper {
@Insert("insert into user values(# {id, # {username} , # fpassword} , # fbirthday})")
    public void save(User user);
@Update("update user set username=# fusername} , password=# {password}where id=# {id}")
        public void update(User user);
@Delete("delete from user where id=# {id}")
    public void delete(int id);
@Select("select * from user where id=#{id}")
    public User findById(int id) ;
@Select("select * from user")
        public List<User> findAl1();

~~~

#### 2、MyBatis的注解实现复杂映射开发

![62809088350](D:\学习资料\近代史\MyBatis的注解实现复杂映射开发.png)![62809102559](D:\学习资料\近代史\MyBatis的注解实现复杂映射开发2.png)

##### 1、方式一

OrderMapper.java

~~~java
public interface OrderMapper {
@Select("select *, o.id oid from orders o, user u where o.uid=u.id")
    @Results( {
@Result(colummn = "oid" , property = "id")，
@Result(colummn = "ordertime" , property = "ordertime")，
@Result(columm = "tota1", property = "tota1")，
@Result(colummn = "uid", property = "user.id")，
@Result(colummn = "username" , property = "user. username")，
@Result(colummn = "password" , property = "user.password")
})
public List<Order> findAll();

~~~

##### 2、方式二

OrderMapper.java

~~~java
public interface OrderMapper {
@Select("select * from orders")
    @Results( {
@Result(column = "id" , property = "id")，
@Result(columm = "ordertime" , property = "ordertime")，@Result(colummn = "total" , property = "tota1")，
@Result(
property = "user"，//要封装的属性名称
columm = "uid"，//根据那个字段去查询user表的数据javaType = User.class，/ /要封装的实体类型
/ /select属性代表查询那个接口的方法获得数据
one = @0ne(select = "com.itheima.mapper.UserMapper.findById")
)
})
public List<Order> findAl1();

~~~

##### 3、一对多的注解开发

~~~java
@Select("select * from user")
@Results({
    @Result(id=true ,columm = "id" , property = "id")，
    @Result(colummn = "username" ,property = "username")，
    @Result(colummn = "pasword" , property = "password")，
    @Result(
        property = "orderList",
        columm = "id"，
        javaType = List.class,
        many = Many(select = "com.itheima.mapper.OrderMapper. findByUid")
    })
~~~

##### 3、多对多查询的模型

用户表和角色表的关系为，一个用广有多个角色，一个角色被多个用户使用多对多查询的需求:查询用户同时查询出该用户的所有角色

![62815556813](D:\学习资料\近代史\多对多查询的模型.png)