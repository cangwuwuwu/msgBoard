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

/**
 * @Author: Cangwu
 * @Date: 2020/5/7 16:37
 * @Description:
 */
@WebServlet("/deleteMsg")
public class deleteMsgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        int mid = Integer.parseInt(req.getParameter("mid"));
        UserService service = new UserServiceImpl();
        int i = service.removeMsgById(mid);

        ObjectMapper mapper = new ObjectMapper();
        // 将对象转为json字符串
        mapper.writeValue(resp.getWriter(), i);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
