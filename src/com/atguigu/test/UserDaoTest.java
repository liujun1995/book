package com.atguigu.test;

import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author LiuJun
 * @create 2021-06-10-18:54
 * @description
 */
public class UserDaoTest {

   private final UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("admin") == null) {
            System.out.println("用户名可用");
        } else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void saveUser() {
        userDao.saveUser(new User(null, "lj", "123456", "lj168@qq.com"));
    }

    //登录
    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin", "admin")==null){
            System.out.println("用户名或密码错误，请重新登录！");
        }else {
            System.out.println("登陆成功!");
        }

    }
}