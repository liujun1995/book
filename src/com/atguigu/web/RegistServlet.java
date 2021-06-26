package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiuJun
 * @create 2021-06-10-21:57
 * @description
 */
public class RegistServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*1.获取请求的参数
        * 2.检查验证码是否正确
        * 正确
        *   检查用户名是否可用
        *   可用：调用service保存到数据库，跳转到注册成功页面regist_success.html
        *   不可用：跳回注册页面
        * 不正确
        *   跳回注册页面*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        //验证码由服务器生成，先写死
        if ("abcd".equalsIgnoreCase(code)){
            //检查用户名是否可用
            if (userService.existsUsername(username)){
                request.setAttribute("msg","用户名已存在！");
                //回显用户名和邮箱
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                //返回true,用户名不可用,跳回注册页面
                System.out.println("用户名[" + username + "]已存在");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/user/regist.jsp");
                requestDispatcher.forward(request, response);
            }else{
                //用户名可用，调用service保存到数据库
                userService.registerUser(new User(null, username, password, email));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/user/regist_success.jsp");
                requestDispatcher.forward(request, response);
            }

        }else {
            //当验证码错误时
            request.setAttribute("msg","验证码错误！");
            //回显用户名和邮箱
            request.setAttribute("username", username);
            request.setAttribute("email", email);

            System.out.println("验证码[" + code + "]错误");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/user/regist.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
