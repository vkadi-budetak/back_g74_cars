package app.controller;

import app.domain.Car;
import app.service.CarService;
import app.service.CarServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class CarController {

    // це робимо для того щоб спілкуватися із service
    private final CarService service = new CarServiceImpl();

    // створюємо контролер який буде приймати із конструктора Car дані ми створюємо автомобіль
    public Car save(String brand, int year, BigDecimal price) {
        Car car = new Car(brand, year, price);
        return service.save(car);
    }

    // метод щоб отримати всі автомобілі
    public List<Car> getAll() {
        return service.getAll();
    }

    public Car getById(Long id) {
        return service.getById(id);
    }

    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
