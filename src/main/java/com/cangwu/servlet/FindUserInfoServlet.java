package com.cangwu.servlet;

import com.cangwu.domain.AllInfo;
import com.cangwu.domain.User;
import com.cangwu.service.UserService;
import com.cangwu.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2020/4/13 23:27
 */
@WebServlet("/findUserInfoServlet")
public class FindUserInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 查看是否有名为number的cookie
                if ("number".equals(cookie.getName())) {

                    UserService service = new UserServiceImpl();
                    List<User> infolist = service.findUserInfo(cookie.getValue());

                    AllInfo<User> allinfo = new AllInfo<User>();
                    allinfo.setNumber(cookie.getValue());
                    allinfo.setList(infolist);

                    ObjectMapper mapper = new ObjectMapper();
                    // 将cookie的值 已登录的学号传到前台
                    mapper.writeValue(response.getWriter(),allinfo);
                }
            }
        }
    }
}