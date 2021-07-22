package com.cangwu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Cangwu
 * @Date: 2020/4/14 0:17
 */
@WebServlet("/clearCookieServlet")
public class ClearCookieServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 退出登录
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("number".equals(cookie.getName())) {
                    // 设置时间为0  等同于删除cookie
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        request.getSession().removeAttribute("Num");
        response.sendRedirect(request.getContextPath() + "/index.html");
    }
}