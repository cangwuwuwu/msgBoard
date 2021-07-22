package com.cangwu.servlet;

import com.cangwu.service.UserService;
import com.cangwu.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2020/4/23 20:47
 */
@WebServlet("/uploadImgServlet")
public class UploadImgServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String stuid = null;
        String a = null;
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

        if (servletFileUpload.isMultipartContent(request)) {

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // 查看是否有名为number的cookie
                    if ("number".equals(cookie.getName())) {
                        stuid = cookie.getValue();
                    }
                }
            }
            // 获取FileItem
            try {
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);
                for (FileItem item : fileItems) {
                    // 获取图片名称
                    String name = item.getName();
                    //获取存放图片的路径
                    String realPath = getServletContext().getRealPath("/img/users");
                    //创建文件对象
                    File file = new File(realPath, name);
                    item.write(file);
                    item.delete();

                    UserService service = new UserServiceImpl();
                    service.insertHeadName(name, stuid);
                    a = "上传成功";
                }
            }catch (FileNotFoundException e0){
                a = "请先点击头像选择图片再上传！";
            }catch (Exception e) {
                e.printStackTrace();
                a = "上传失败！即将返回";
            }
            response.getWriter().write("<script>alert('"+a+"')</script>");
        }
        response.setHeader("refresh",".5;url=personalpage.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}