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
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Cangwu
 * @Date: 2020/4/2 17:35
 */
@WebServlet("/findUserByIdServlet")
public class FindUserByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        String number = request.getParameter("number");

        UserService findUserService = new UserServiceImpl();
        Boolean b = findUserService.findUserByNumber(number);

        Map<String, Object> map = new HashMap<String, Object>();
        if (!b) {
            // 邮箱已被注册
            map.put("IdExsit",true);
            map.put("msg","此用户已被注册");
        } else {
            // 不存在
            map.put("IdExsit",false);
            map.put("msg","该学号可以使用");
        }
        if (number.equals("")) {
            map.put("IdExsit",true);
            map.put("msg","");
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
