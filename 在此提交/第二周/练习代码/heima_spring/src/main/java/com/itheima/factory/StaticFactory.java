package com.itheima.factory;

import com.xxx.UserDao;
import com.xxx.impl.UserImpl;

public class StaticFactory {
    public static UserDao getUserDao(){
        return new UserImpl();
    }
}
