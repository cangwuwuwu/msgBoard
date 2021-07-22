package com.cangwu.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: Cangwu
 * @Date: 2020/3/27 23:54
 */
public class DataSourceUtils {

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    // 获取数据源
    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void CloseResource(Connection conn , Statement stmt, ResultSet rs) {
        closeResultSet(rs);
        closeStaement(stmt);
        closeConn(conn);
    }

    public static void closeConn(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                conn = null ;
            }
        }
    }

    /**
     * 释放语句执行者
     * @param st
     * 语句执行者
     */
    public static void closeStaement(Statement st){
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                st = null ;
            }
        }
    }

    /**
     * 释放结果集
     * @param rs
     * 结果集
     */
    public static void closeResultSet(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                rs = null ;
            }
        }
    }
}
