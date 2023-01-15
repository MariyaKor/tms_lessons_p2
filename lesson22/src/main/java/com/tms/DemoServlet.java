package com.tms;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.println("Hello from DemoServlet! ");
        outputStream.close();
    }
}