package app.service;

import app.domain.Car;

import java.util.List;

public interface CarService {

    //пишемо метод по збереженню автомобілів
    Car save(Car car);

    // метод який повертає всі автомобілі
    List<Car> getAll();

    Car getById(Long id);
    void deleteById(Long id);
}

