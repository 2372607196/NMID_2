package com.itheima.factory;

import com.xxx.UserDao;
import com.xxx.impl.UserImpl;

public class DynamicFactory {

    public UserDao getUserDao(){
        return new UserImpl();
}
}
