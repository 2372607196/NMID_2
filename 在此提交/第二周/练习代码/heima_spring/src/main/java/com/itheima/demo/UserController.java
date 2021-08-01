package com.itheima.demo;

import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserController {

    public static void main(String[] args) {
//        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext app = new FileSystemXmlApplicationContext("D:\\学习资料\\java\\heima_spring\\src\\main\\resources\\applicationContext.xml");
        UserService userService = (UserService) app.getBean("userService");
        UserService userService1 = app.getBean(UserService.class);
        userService.save();
    }
}
