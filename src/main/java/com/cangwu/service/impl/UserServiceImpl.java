package com.cangwu.service.impl;

import com.cangwu.dao.UserDao;
import com.cangwu.dao.impl.UserDaoImpl;
import com.cangwu.domain.Message;
import com.cangwu.domain.PageBean;
import com.cangwu.domain.User;
import com.cangwu.service.UserService;

import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2020/4/11 20:33
 */
public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public Boolean findUserByNumber(String number) {
        return dao.findUser(number);
    }

    @Override
    public PageBean<Message> findMsgByPage(String currentPage, String rows) {

        int intCurrentPage = Integer.parseInt(currentPage);
        int intRows = Integer.parseInt(rows);

        PageBean<Message> pb = new PageBean<Message>();
        pb.setCurrentPage(intCurrentPage);
        pb.setRows(intRows);

        int totalCount = dao.findTotalCount();
        pb.setTotalCount(totalCount);
        // 计算开始的记录索引
        int start = (intCurrentPage - 1) * intRows;
        List<Message> list = dao.findByPage(start, intRows);
        pb.setList(list);

        // 计算总页码
        int totalPage = (totalCount % intRows) == 0 ? totalCount / intRows : (totalCount / intRows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public List<User> loginByNumber(String number, String password) {
        return dao.loginByNum(number, password);
    }

    @Override
    public Boolean registerAll(String email, String password, String number, String username, String sex, String phoneNum, String birthday) {
        return dao.registUser(email, password, number, username, sex, phoneNum, birthday);
    }

    @Override
    public void saveMsgByDao(String name, String msg, String time) {
        dao.saveMsg(name, msg, time);
    }

    @Override
    public int findAccessTimes(int hasId) {
        return dao.findAccessTimesByDao(hasId);
    }

    @Override
    public List<User> findUserInfo(String value) {
        return dao.findUserInfoByDao(value);
    }

    @Override
    public void insertHeadName(String name, String stuid) {
        dao.insertHeadNameByDao(name, stuid);
    }

    @Override
    public int removeMsgById(Integer mid) {
        return dao.deleteMsgByDao(mid);
    }


}
