package com.cangwu.servlet;

import com.cangwu.service.UserService;
import com.cangwu.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Cangwu
 * @Date: 2020/4/4 10:02
 */
@WebServlet("/sensitiveWordsServlet")
public class SensitiveWordsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String name = request.getParameter("name");
        String msg = request.getParameter("msg");

        // 格式化日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = URLEncoder.encode(simpleDateFormat.format(date), "UTF-8");
        String currentTimeDecode = URLDecoder.decode(currentTime, "utf-8");

        String sender = (String) request.getSession().getAttribute("Num");

        // 将获取到的留言和名称信息封装到map中
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        map.put("name", name);
        map.put("msg", msg);
        map.put("time", currentTimeDecode);

        // 提交map
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), map);

        saveMsg(name, msg, currentTimeDecode);
    }

    /**
     * 将昵称 留言 时间存储到数据库
     *
     * @param name 昵称 name
     * @param msg  留言 msg
     * @param time 留言时间 currentTimeDecode
     */
    private void saveMsg(String name, String msg, String time) {
        UserService service = new UserServiceImpl();
        service.saveMsgByDao(name, msg, time);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
