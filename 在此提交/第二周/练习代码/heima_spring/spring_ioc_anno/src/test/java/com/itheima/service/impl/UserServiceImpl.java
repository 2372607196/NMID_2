package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

//<bean id="userService" class="com.itheima.service.impl.UserServiceImpl">
//</bean>

//@Component("userService")
@Service("userService")
//@Scope("prototype")
@Scope("singleton")
public class UserServiceImpl implements UserService {

    @Value("${jdbc.driver}")
    private String driver;

    //<property name="userDao" ref="userDao"></property>
    //@Autowired//按照数据类型从Spring容器中进行匹配的
    //@Qualifier("userDao")//是按照id值从容器中进行匹配的，但是主要此处@Qualifier结合@Autowired一起使用
    @Resource(name="userDao")//@Resource相当于@Qulifier+@Autowired
    private UserDao userDao;

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    public void save(){
        userDao.save();
        System.out.println(driver);
    }
    @PostConstruct
    public void init(){
        System.out.println("chushihua");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("xioahui");
    }
}
