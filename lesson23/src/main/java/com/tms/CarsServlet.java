package com.tms;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 1.Создать сервлет, который будет использоваться для получение и сохранения данных о машинах. Он должен:
 * a. Получать список всех машин
 * b. Получить машину по ее id
 * c. Сохранить данные о новой машине
 * d. Обновить данные существующей машины
 * e. Удалить машину.
 * 2.Вернуть в куках время последнего обращения к серверу
 */
@WebServlet(urlPatterns = {"/cars"})
public class CarsServlet extends HttpServlet {

    private ConcurrentMap<String, String> carsInfo = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.println(getLastDateTimeAccess(req));
        String id = req.getParameter("id");
        if (id != null) {
            outputStream.println("cost = " + carsInfo.get(id));
        } else {
            outputStream.println("cars: " + carsInfo);
        }
        outputStream.close();
    }

    private String getLastDateTimeAccess(HttpServletRequest req) throws IOException {
        String value = null;
        Cookie[] cookies = req.getCookies();
        String cookieName = "lastAccessTime";
        Cookie cookie = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        if (cookie != null) {
            value = cookie.getValue();
        }
        return value;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String cost = req.getParameter("cost");
        carsInfo.put(id, cost);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Scanner scanner = new Scanner(req.getInputStream(), StandardCharsets.UTF_8);
        String body = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
        System.out.println("body: " + body);
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            String id = String.valueOf(jsonObject.get("id"));
            String cost = (String) jsonObject.get("cost");
            carsInfo.put(id, cost);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        carsInfo.remove(id);
    }

}