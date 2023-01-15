package com.tms;

import com.tms.model.Car;
import com.tms.service.impl.CookieServiceImpl;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    private ConcurrentMap<String, Car> cars = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.println(new CookieServiceImpl().getLastDateTimeAccess(req));
        String id = req.getParameter("id");
        if (id == null) {
            outputStream.println("cars: " + cars);
        } else if (id.isBlank() || cars.get(id) == null) {
            resp.setStatus(400);
            outputStream.println("Specified id isn't valid");
        } else {
            outputStream.println("cars " + cars.get(id));
        }
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        String id = req.getParameter("id");
        if (id == null || id.isBlank()) {
            resp.setStatus(400);
            outputStream.println("Specified id isn't valid");
        } else if (cars.get(id) != null) {
            resp.setStatus(422);
            outputStream.println("Car with this id already exists");
        } else {
            String model = req.getParameter("model");
            String cost = req.getParameter("cost");
            cars.put(id, new Car(model, cost));
        }
        outputStream.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        String id = req.getParameter("id");
        if (id == null || id.isBlank()) {
            resp.setStatus(400);
            outputStream.println("Specified id isn't valid");
        } else if (cars.get(id) == null) {
            resp.setStatus(422);
            outputStream.println("Car with this id doesn't exists");
        } else {
            String model = req.getParameter("model");
            String cost = req.getParameter("cost");
            cars.put(id, new Car(model, cost));
        }
        outputStream.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        String id = req.getParameter("id");
        if (id == null || id.isBlank()) {
            resp.setStatus(400);
            outputStream.println("Specified id isn't valid");
        } else if (cars.get(id) == null) {
            resp.setStatus(422);
            outputStream.println("Car with this id doesn't exists");
        } else {
            cars.remove(id);
        }
        outputStream.close();
    }
}