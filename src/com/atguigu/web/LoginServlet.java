package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiuJun
 * @create 2021-06-11-13:18
 * @description 登录业务
 */
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*1.获取请求的参数
        * 2.调用userService处理登陆业务
        * 3.根据login()方法的返回结果判断登录是否成功
        * 成功：跳转到login_success.jsp
        * 失败：跳转回登录页面*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser==null){
            //保存错误信息，进行回写
            request.setAttribute("msg","用户名或密码错误！");
            request.setAttribute("username", username);
            //跳回登录页面
            request.getRequestDispatcher("/pages/user/login.jsp")
                    .forward(request, response);
        }else {
            //登陆成功，跳转到login_success.html
            request.getRequestDispatcher("/pages/user/login_success_menu.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
