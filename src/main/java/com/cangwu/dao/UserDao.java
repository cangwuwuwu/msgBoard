package com.cangwu.dao;

import com.cangwu.domain.Message;
import com.cangwu.domain.User;

import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2020/4/11 20:13
 */
public interface UserDao {
    /**
     * 查找是否存在用户
     * @param number 学号
     * @return
     */
    Boolean findUser(String number);

    /**
     * 查询总记录数
     * @return int
     */
    int findTotalCount();

    /**
     * 分页查询每页记录
     * @param start 起始页
     * @param intRows 行数
     * @return 留言的list集合
     */
    List<Message> findByPage(int start, int intRows);


    /**
     *  登录查询
     * @param number
     * @param password
     * @return
     */
    List<User> loginByNum(String number, String password);

    /**
     * 注册插入
     * @param email
     * @param password
     * @param number
     * @param username
     * @param sex
     * @param phoneNum
     * @param birthday
     * @return
     */
    Boolean registUser(String email, String password, String number, String username, String sex, String phoneNum, String birthday);

    /**
     * 存储留言
     * @param name
     * @param msg
     * @param time
     */
    void saveMsg(String name, String msg, String time);


    int findAccessTimesByDao(int hasId);

    List<User> findUserInfoByDao(String value);

    void insertHeadNameByDao(String name,String stuid);

    int deleteMsgByDao(Integer mid);
}
