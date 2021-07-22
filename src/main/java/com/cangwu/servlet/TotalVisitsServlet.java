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
 * @Date: 2020/4/19 11:06
 */

@WebServlet("/totalVisitsServlet")
public class TotalVisitsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        // 通过session防止刷访问量
        Object id = request.getSession().getId();
        Object visitSession = request.getSession().getAttribute("VisitSession");
        int hasId = 0;
        if (visitSession == null) {
            hasId = 1;
            request.getSession().setAttribute("VisitSession", id);
        }

        UserService service = new UserServiceImpl();
        int accessTimes = service.findAccessTimes(hasId);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), accessTimes);
    }
}

