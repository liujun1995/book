package com.atguigu.test;

import com.atguigu.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiuJun
 * @create 2021-06-10-13:48
 * @description
 */
public class JDBCUtilsTest {

    private static final int max_size=10;
    @Test
    public void testJDBC(){

        getList();
        List<String> list = new ArrayList<>();

    }

    public void getList() {
        for (int i = 0; i < 100; i++) {

            Connection conn = JDBCUtils.getConnection();
            System.out.println(conn);

        }

    }

}
