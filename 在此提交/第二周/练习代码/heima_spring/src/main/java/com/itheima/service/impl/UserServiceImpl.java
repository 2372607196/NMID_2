package com.itheima.service.impl;

import com.itheima.service.UserService;
import com.xxx.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    public UserServiceImpl(){
    }
//    public void setUserDao(UserDao userDao){
//        this.userDao = userDao;
//    }
    public void save(){
//        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
//        UserDao userDao1 = (UserDao)app.getBean("userDao");
        userDao.save();
    }
}
