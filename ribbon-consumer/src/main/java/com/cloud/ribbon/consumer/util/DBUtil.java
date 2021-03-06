package com.cloud.ribbon.consumer.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();// 加载Oracle驱动程序
        } catch (InstantiationException e1) {
            System.out.println("实例异常");
        } catch (IllegalAccessException e2) {
            System.out.println("访问异常");
        } catch (ClassNotFoundException e3) {
            System.out.println("Oracle驱动类找不到");
        }
    }
    /***
     * 返回一个数据库连接
     */
    public static Connection getConnection(String url, String user,String password) {
        Connection connection = null;// 创建一个数据库连接
        try {
            connection = DriverManager.getConnection(url, user, password);// 获取连接
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

