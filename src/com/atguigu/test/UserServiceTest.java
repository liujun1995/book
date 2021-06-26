package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author LiuJun
 * @create 2021-06-10-21:06
 * @description
 */
public class UserServiceTest {

    private UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null, "bj168", "666", "bj168@qq.com"));
        userService.registerUser(new User(null, "abc169", "667", "abc169@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "wz128", "bbj", null)));
        System.out.println(userService.login(new User(null, "lj", "123456", null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("lj")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}