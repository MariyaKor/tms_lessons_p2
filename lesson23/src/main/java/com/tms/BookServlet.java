package com.tms;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Проверить редирект на html страницу
 */
@WebServlet("/book")
public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addCookie(new Cookie("lastAccessTime", String.valueOf(LocalDateTime. now())));
        req.getRequestDispatcher("static/book.html").forward(req, resp);
    }
}
