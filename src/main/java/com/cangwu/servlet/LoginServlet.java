package com.cangwu.servlet;

import com.cangwu.domain.User;
import com.cangwu.service.UserService;
import com.cangwu.service.impl.UserServiceImpl;

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
 * @Date: 2020/3/27 20:59
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String number = request.getParameter("email_id");
        String password = request.getParameter("password");
        String inputCode = request.getParameter("inputCode");
        String randomCode = (String) request.getSession().getAttribute("changeCode");

        // 调用service层，向数据库中查询用户名和密码
        UserService service = new UserServiceImpl();
        List<User> user = service.loginByNumber(number, password);

        boolean allreadyLogin = false;
        Cookie[] getCookies = request.getCookies();
//        System.out.println(getCookies);
        if (getCookies != null) {
            for (Cookie cookie : getCookies) {
                if ("number".equals(cookie.getName())) {
                    allreadyLogin = true;
                }
            }
        }
        if (allreadyLogin) {
            String b = "您已经登录了一个账号了！你可以退出登录重试一次~";
            response.getWriter().write("<script>alert('" + b + "')</script>");
            response.setHeader("refresh", "1;url=index.html");
        } else if (user.toString() != "[]" && inputCode.equals(randomCode)) {
            // 登录成功则记录session和cookie 用于自动登陆
            Cookie cookie = new Cookie("number", number);
            // cookie一小时内有效
            cookie.setMaxAge(60 * 60);
            // 返回给浏览器以便判断
            response.addCookie(cookie);
            // 创建Session 设置一小时
            request.getSession().setMaxInactiveInterval(60 * 60);
            request.getSession().setAttribute("Num", number);
            response.sendRedirect("index.html");
        } else {
            String a = "登录错误！即将返回到登录页面";
            response.getWriter().write("<script>alert('" + a + "')</script>");
            response.setHeader("refresh", "1;url=login.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
