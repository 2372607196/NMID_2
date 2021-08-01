package com.xxx.impl;

import com.itheima.domain.User;
import com.xxx.UserDao;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class UserImpl implements UserDao {

    private List<String> strList;
    private Map<String,User> UserMap;
    private Properties properties;

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public void setUserMap(Map<String, User> userMap) {
        UserMap = userMap;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private String username;
    private int age;

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }
//    public UserImpl(){
//        System.out.println("UserDaoImpl创建....");
//    }
//
//    public void init(){
//        System.out.println("初始化方法....");
//    }
//
//    public void destroy(){
//        System.out.println("销毁方法....");
//    }

    public void save(){
//        System.out.println(username+"===="+age);
        System.out.println(strList);
        System.out.println(UserMap);
        System.out.println(properties);
        System.out.println("save running....");
    }
}
