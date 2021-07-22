package com.cangwu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2020/4/4 9:26
 */
@WebFilter("/sensitiveWordsServlet")
public class SensitiveWordsFilter implements Filter {

    private List<String> list = new ArrayList<String>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            // 加载读取Sensitive.txt文件
            ServletContext servletContext = filterConfig.getServletContext();
            String realPath = servletContext.getRealPath("/SensitiveWords.txt");
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line = null;
            while ((line = br.readLine()) != null) {
                // 把敏感词加入到list列表中去
                list.add(line);
            }
            // 释放资源
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest proxy_req = (ServletRequest) Proxy
                .newProxyInstance(servletRequest.getClass().getClassLoader(),
                        servletRequest.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 增强servletRequest.getParameter()方法
             * @param proxy
             * @param method
             * @param args
             * @return value
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String key = "getParameter";
                if (key.equals(method.getName())) {
                    // 增强返回值
                    String value = (String) method.invoke(servletRequest, args);
                    if (value != null) {
                        for (String str : list) {
                            if (value.contains(str)) {
                                int sensitiveNum = str.length();
                                StringBuilder star = new StringBuilder();
                                for (int i = 1; i <= sensitiveNum; ++i) {
                                    star.append("*");
                                }
                                value = value.replaceAll(str, star.toString());
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(servletRequest, args);
            }
        });
        filterChain.doFilter(proxy_req,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
