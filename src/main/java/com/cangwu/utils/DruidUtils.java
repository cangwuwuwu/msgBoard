package com.cangwu.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author: Cangwu
 * @Date: 2020/4/1 22:48
 */
public class DruidUtils {

    private static DataSource ds ;

    static {
        try {
            // 加载配置文件
            Properties pro = new Properties();
            pro.load(DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            // 获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 释放资源
     */
    public static void close(Statement stmt , Connection conn) {
        close(null,stmt,conn);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn ) {

        if (rs != null ) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null ) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null ) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取连接池的方法
     */
    public static DataSource getDataSource() {
        return ds;
    }
}
