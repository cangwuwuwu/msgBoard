package com.cangwu.service;

import com.cangwu.domain.Message;
import com.cangwu.domain.PageBean;
import com.cangwu.domain.User;

import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2020/4/11 20:33
 */
public interface UserService {
    /**
     * 检查用户是否已被注册
     * @param number 注册时输入的学号
     * @return 查询结果集
     */
    Boolean findUserByNumber(String number);

    /**
     * 分页查询
     * @param currentPage 当前页
     * @param rows 每页行数
     * @return 每页的留言数据
     */
    PageBean<Message> findMsgByPage(String currentPage, String rows);

    /**
     * 登录
     * @param number
     * @param password
     * @return
     */
    List<User> loginByNumber(String number, String password);

    /**
     * 注册用户
     * @param email 邮箱
     * @param password 密码
     * @param number 学号
     * @param username 用户名
     * @param sex 性别
     * @param phoneNum 电话号码
     * @param birthday 生日
     * @return boolean
     */
    Boolean registerAll(String email, String password, String number, String username, String sex, String phoneNum, String birthday);

    /**
     * 存储留言到数据库
     * @param name 昵称
     * @param msg 留言
     * @param time 时间
     */
    void saveMsgByDao(String name, String msg, String time);

    int findAccessTimes(int hasId);

    List<User> findUserInfo(String value);

    void insertHeadName(String name,String stuid);

    int removeMsgById(Integer mid);
}
