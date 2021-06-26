package com.atguigu.dao.impl;

import com.atguigu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LiuJun
 * @create 2021-06-10-14:00
 * @description
 */
public abstract class BaseDao<T>{
    //使用DbUtils操作数据库
    private static QueryRunner queryRunner = new QueryRunner();

    /**
     * update语句用来执行Insert/Update/Delete语句
     * @param sql
     * @param args
     * @return -1说明执行失败
     */
    public int update(String sql,Object ... args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个javaBean的sql语句
     * @param type user.class
     * @param sql 执行的sql语句
     * @param args sql占位符参数
     * @param '<T>' 返回的类型
     * @return
     */
    public T queryForOne(Class<T> type,String sql,Object ... args){

        Connection connection = JDBCUtils.getConnection();
        try {
           return queryRunner.query(connection, sql, new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询多个javaBean的sql语句
     * @param type
     * @param sql
     * @param args
     * @return
     */
    public  List<T> queryForLIst(Class<T> type,String sql,Object ... args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     *  执行返回一行一列的sql语句
     * @param sql
     * @param args sql对应占位符的参数值
     * @return
     */
    public Object queryForSingleValue(String sql,Object ... args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
