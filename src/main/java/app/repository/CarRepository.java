package app.repository;

import app.domain.Car;

import java.util.List;

public interface CarRepository {

    // тут повинен реалізуватися абстрактний метод - обовязковий для реалізації
    // тобто Car він є abstract
    Car save(Car car); // получаем и отправляем
    List<Car> findAll(); // метод пповетає список всіх автоболів
    Car findById(Long id); // метод для пошуку
    void deleteById(Long id); // метод для видалення і нічого не повертає
}

