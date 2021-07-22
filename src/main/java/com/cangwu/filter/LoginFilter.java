package com.cangwu.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Cangwu
 * @Date: 2020/4/3 15:19
 */
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    private static final String SENDURI = "sensitiveWordsServlet";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 强转为HttpServletRequest 否则无法获取资源请求路径
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");

        Object user = request.getSession().getAttribute("Num");
        String uri = request.getRequestURI();

        if (uri.contains(SENDURI) && user == null) {
            // 未登录 跳转到登录界面
            Map<String, Object> map = new HashMap<>();
            map.put("code", 401);
            map.put("msg", "未登录");

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), map);
        } else {
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}
