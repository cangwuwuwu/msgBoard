package com.cangwu.servlet;

import com.cangwu.service.UserService;
import com.cangwu.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * @Author: Cangwu
 * @Date: 2020/3/23 19:53
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String number = request.getParameter("number");

        String username = request.getParameter("username");
        String sex = request.getParameter("inlineRadioOptions");
        String phoneNum = request.getParameter("phoneNum");
        String birthday = request.getParameter("birthday");
        String inputCode = request.getParameter("inputCode");
        String randomCode = (String)request.getSession().getAttribute("changeCode");

        UserService service = new UserServiceImpl();
        Boolean registSuccess = service.registerAll(
                email,password,number,username,sex,phoneNum,birthday);

        if (password.equals(rePassword) && inputCode.equals(randomCode) && registSuccess) {
            // 验证成功 跳转到登录页面
            response.setStatus(302);
            response.setHeader("Location","login.html");
        } else {
            // 验证失败 弹出提示刷新页面
            PrintWriter out = response.getWriter();
            String a = URLEncoder.encode("注册失败..请检查","UTF-8");
            out.print("<script>alert(decodeURIComponent('"+a+"'))</script>");
            response.setHeader("refresh","1;url=register.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
