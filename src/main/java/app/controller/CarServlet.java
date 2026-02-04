package app.controller;

import app.domain.Car;
import app.service.CarService;
import app.service.CarServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * CarServlet - прописуємо свої Servlet - справній http запроси
 * Логіка прийому і відправки http запроса
 */

public class CarServlet extends HttpServlet {

    private final CarService service = new CarServiceImpl();

    // починаю пистаи метод doGet і мені запропонує метод від HttpServlet(тобто мы наслідуємся від HttpServlet)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET http://10.20.30.40.8080/cars
        // GET http://10.20.30.40.8080/cars?id=5

        // 1) проводимо req - отримання
        // як читати параметр??
        String id = req.getParameter("id");

        // 2) проводимо resp (respons) - щоб відправити
        // нам допоможе бібліотека яку ми скачали jackson (переобразовує java в текст і навпаки)
        ObjectMapper mapper = new ObjectMapper();
        Writer writer = resp.getWriter();
        resp.setContentType("application/JSON");


        if (id == null) {
            // всі хоче автомобілі
            List<Car> cars = service.getAll();
            mapper.writeValue(writer, cars); // передаються 2 агрумента (writer, cars) - куди ми пишемо і що ми відправляємо
        } else {
            // отримуємо по id
            Long numericId = Long.parseLong(id); // "5" -> 5 - перетворюємо із рядка в цифру
            Car car = service.getById(numericId);
            mapper.writeValue(writer, car); // передаються 2 агрумента (writer, car) - куди ми пишемо і що ми відправляємо
        }
    }

            @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            Car car = mapper.readValue(req.getReader(), Car.class);
            Car savedCar = service.save(car);
            resp.setContentType("Application/JSON");
            mapper.writeValue(resp.getWriter(), savedCar);
        }

        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
            String id = req.getParameter("id");
            Long numericId = Long.parseLong(id);
            service.deleteById(numericId);
        }
}
