package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author LiuJun
 * @create 2021-06-15-15:23
 * @description
 */
public class WebUtils {

    public static <T> T copyParamToBean(Map ParamMap, T javaBean) {
        try {
            //System.out.println("注入之前" + javaBean);
            BeanUtils.populate(javaBean, ParamMap);
            //System.out.println("注入之后" + javaBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return javaBean;
    }

    public static Integer parseInt(String str,Integer defaultValue){
        if (str==null||"".equals(str)){
            return defaultValue;
        }else {
            return Integer.parseInt(str);
        }
    }
}
