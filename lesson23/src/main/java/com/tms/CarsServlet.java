package com.tms;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CarsServlet extends HttpServlet {

    private ConcurrentMap<String, String> carsInfo = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        if (req.getParameter("id") != null) {
            outputStream.println("brand = " + carsInfo.get("id"));
        } else {
            outputStream.println("cars  " + carsInfo);
        }
        outputStream.close();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String brand = req.getParameter("brand");
        carsInfo.put(id, brand);
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
            String brand = (String) jsonObject.get("brand");
            carsInfo.put(id, brand);
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