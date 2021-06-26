package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * @author LiuJun
 * @create 2021-06-10-17:16
 * @description
 */
public interface UserDao {

    //alt+shift+s然后Generate

    /**
     * 根据用户名查询用户信息，验证表单的用户名是否存在
     * @param username
     * @return 如果返回null说明没有此用户，查到说明用户名不可用
     */
    public User queryUserByUsername(String username);

    /**
     * 保存用户信息，注册时
     * @param user
     * @return 返回-1，表示操作失败
     */
    public int saveUser(User user);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null,说明用户名或密码错误
     */
    public User queryUserByUsernameAndPassword(String username,String password);

}
