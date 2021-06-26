package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @author LiuJun
 * @create 2021-06-10-20:06
 * @description
 */
public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回null，登录失败
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已经存在，false表示用户可用
     */
    public boolean existsUsername(String username);

}
