package com.cangwu.servlet;

import com.cangwu.domain.Message;
import com.cangwu.domain.PageBean;
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
 * @Date: 2020/4/7 21:17
 */
@WebServlet("/displayMsgServlet")
public class DisplayMsgServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String currentPage = request.getParameter("currentPage");

        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }

        // 调用service查询
        UserService service = new UserServiceImpl();
        PageBean<Message> pb = service.findMsgByPage(currentPage,"6");

        ObjectMapper mapper = new ObjectMapper();
        // 将对象转为json字符串
        mapper.writeValue(response.getWriter(),pb);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
