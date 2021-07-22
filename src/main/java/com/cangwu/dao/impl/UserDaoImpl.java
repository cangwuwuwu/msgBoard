package com.cangwu.dao.impl;

import com.cangwu.dao.UserDao;
import com.cangwu.domain.Message;
import com.cangwu.domain.User;
import com.cangwu.utils.DruidUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Author: Cangwu
 * @Date: 2020/4/11 20:24
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());

    @Override
    public Boolean findUser(String number) {
        Boolean b = true;
        String sql = "select * from tb_web where number=?";
        try {
            Map<String, Object> stringObjectMap = template.queryForMap(sql, number);
            if (stringObjectMap != null) {
                b = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public int findTotalCount() {
        String sql = "select count(*) from tb_msgboard where status=1";
        return template.queryForObject(sql, Integer.class);
    }

    @Override
    public List<Message> findByPage(int start, int intRows) {
        String sql = "select * from tb_msgboard where status=1 limit ? , ? ";
        return template.query(sql, new BeanPropertyRowMapper<Message>(Message.class), start, intRows);
    }

    @Override
    public List<User> loginByNum(String number, String password) {
        String sql = "select * from tb_web where number=? and password=?";
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), number, password);
    }

    @Override
    public Boolean registUser(String email, String password, String number, String username, String sex, String phoneNum, String birthday) {
        Boolean registSuccess = false;
        String sql = "insert into tb_web (email,password,number,username,sex,phonenum,birthday)values(?,?,?,?,?,?,?)";
        int rows = template.update(sql, email, password, number, username, sex, phoneNum, birthday);
        if (rows != 0) {
            registSuccess = true;
        }
        return registSuccess;
    }

    @Override
    public void saveMsg(String name, String msg, String time) {
        String sql = "insert into tb_msgboard (mname,mmsg,mtime) values(?,?,?)";
        template.update(sql, name, msg, time);
    }

    @Override
    public int findAccessTimesByDao(int hasId) {
        String sql = "update tb_access set accesstimes = accesstimes + ?";
        template.update(sql, hasId);
        String sql1 = "select * from tb_access";
        return template.queryForObject(sql1, Integer.class);
    }

    @Override
    public List<User> findUserInfoByDao(String value) {
        String sql = "select * from tb_web where number = ?";
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), value);
    }

    @Override
    public void insertHeadNameByDao(String name, String stuid) {
        String sql = "update tb_web set headimg = ? where number = ?";
        template.update(sql, name, stuid);
    }

    @Override
    public int deleteMsgByDao(Integer mid) {
        String sql = "update tb_msgboard set status = 0 where id = ?";
        return template.update(sql, mid);
    }

}
