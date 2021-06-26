package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuJun
 * @create 2021-06-15-10:02
 * @description
 */
public class UserServlet extends BaseServlet {


    private UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*1.获取请求的参数
         * 2.调用userService处理登陆业务
         * 3.根据login()方法的返回结果判断登录是否成功
         * 成功：跳转到login_success.jsp
         * 失败：跳转回登录页面*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());

        User loginUser = userService.login(user);
        if (loginUser == null) {
            //保存错误信息，进行回写
            request.setAttribute("msg", "用户名或密码错误！");
            request.setAttribute("username", username);
            //跳回登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            //登陆成功，跳转到login_success.html
            request.getSession().setAttribute("user", loginUser);
            request.getRequestDispatcher("/pages/user/login_success_menu.jsp")
                    .forward(request, response);
        }

    }

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*1.获取请求的参数
         * 2.检查验证码是否正确
         * 正确
         *   检查用户名是否可用
         *   可用：调用service保存到数据库，跳转到注册成功页面regist_success.html
         *   不可用：跳回注册页面
         * 不正确
         *   跳回注册页面*/
        //获取session中的验证码
        String token = (String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        //删除session中的验证码
        request.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
        String code = request.getParameter("code");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");


        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());

        //验证码由服务器生成，先写死
        if (token!=null&&token.equalsIgnoreCase(code)) {
            //检查用户名是否可用
            if (userService.existsUsername(username)) {
                request.setAttribute("msg", "用户名已存在！");
                //回显用户名和邮箱
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                //返回true,用户名不可用,跳回注册页面
                System.out.println("用户名[" + username + "]已存在");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/user/regist.jsp");
                requestDispatcher.forward(request, response);
            } else {
                //用户名可用，调用service保存到数据库
                userService.registerUser(user);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/user/regist_success.jsp");
                requestDispatcher.forward(request, response);
            }

        } else {
            //当验证码错误时
            request.setAttribute("msg", "验证码错误！");
            //回显用户名和邮箱
            request.setAttribute("username", username);
            request.setAttribute("email", email);

            System.out.println("验证码[" + code + "]错误");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/user/regist.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数username
        String username = request.getParameter("username");
        //调用existUsername
        boolean existName = userService.existsUsername(username);
        //把返回的结果封装成为Map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existName", existName);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*销毁session中的用户信息*/
        request.getSession().invalidate();
        /*重定向到首页,重定向前一定要加工程路径名*/

        response.sendRedirect(request.getContextPath());
    }
}
