package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author LiuJun
 * @create 2021-06-10-12:53
 * @description 获取连接与关闭连接
 */
public class JDBCUtils {


    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadConn = new ThreadLocal<>();


    static {
        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性的配置文件
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接,
     *
     * @return conn 如果返回null,说明获取连接失败
     */
    public static Connection getConnection() {
        Connection conn = threadConn.get();
        if (conn==null){
            try {
                conn = dataSource.getConnection();
                //设置手动管理事务
                conn.setAutoCommit(false);
                //保存到Threadlocal对象中，共后面JDBC操作使用
                threadConn.set(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务，并释放连接
     */
    public static void commitAndClose(){
        Connection connection = threadConn.get();
        if (connection!=null){
            try {
                //提交事务，
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
               // 关闭连接
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //执行remove释放conn
            threadConn.remove();
        }

    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose(){
        Connection connection = threadConn.get();
        if (connection!=null){
            try {
                //提交事务，
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                // 关闭连接
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //执行remove释放conn
            threadConn.remove();
        }
    }

   /* *//**
     * 关闭数据库连接池，放回数据库连接池
     *
     * @param conn
     *//*
    public static void closeConnection(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }*/
}
