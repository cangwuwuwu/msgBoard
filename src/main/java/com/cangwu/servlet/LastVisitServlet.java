package com.cangwu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Cangwu
 * @Date: 2020/4/17 20:48
 */
@WebServlet("/lastVisitServlet")
public class LastVisitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String lastAccessTimeById = null;

        // 获得客户端携带lastAccessTime 的cookie
        Cookie[] cookies = request.getCookies();
        String lastTime = null;
        if (cookies != null) {
            // 读取cookie中的学号 作为时间的cookie的名称 从而达到 不同用户计算不同访问时间
            for (Cookie cookie1 : cookies) {
                if ("number".equals(cookie1.getName())) {
                    lastAccessTimeById = cookie1.getValue();
                }
            }
            if (lastAccessTimeById != null) {
                for (Cookie cookie2 : cookies) {
                    if (lastAccessTimeById.equals(cookie2.getName())) {
                        lastTime = URLDecoder.decode(cookie2.getValue(), "UTF-8");
                    }
                }
            }
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = URLEncoder.encode(simpleDateFormat.format(date),"UTF-8");

        // 创建Cookie 记录当前的最新的访问时间
        Cookie lastAccessTime = new Cookie(lastAccessTimeById,currentTime);
        // cookie 保留一个月
        lastAccessTime.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(lastAccessTime);

        ObjectMapper mapper = new ObjectMapper();
        if (lastTime == null) {
            mapper.writeValue(response.getWriter(),"是本月第一次访问");
        } else {
            Date last = null,now = null;
            try {
                last = simpleDateFormat.parse(URLDecoder.decode(lastTime,"UTF-8"));
                now = simpleDateFormat.parse(URLDecoder.decode(currentTime,"UTF-8"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long lastSecond = last.getTime();
            long nowSecond = now.getTime();
            long minutes = ((nowSecond - lastSecond) / (1000 * 60));
            long hours = minutes / 60;
            long days = hours / 24;
            if (days > 0) {
                mapper.writeValue(response.getWriter(), days +"天前来过");
            } else if (hours > 0) {
                mapper.writeValue(response.getWriter(), hours +"小时前来过");
            } else if (minutes > 2){
                mapper.writeValue(response.getWriter(), minutes +"分钟前来过");
            } else {
                mapper.writeValue(response.getWriter(),"刚刚来过");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
