package app.service;

import app.domain.Car;
import app.repository.CarRepository;
import app.repository.CarRepositoryMap;

import java.util.List;

public class CarServiceImpl implements CarService {

    // це робимо для того щоб спілкуватися із репозиторієм
    private final CarRepository repository = new CarRepositoryMap();

    @Override
    public Car save(Car car) {
        //В реальном сервесе здесь может быть какая-то обработка
        // поступившего обьекта автомобиля? а также проверка данных
        // на корректность, например, на пустое ли наименование,
        // не отрицательная ли цена и т.д.
        return repository.save(car);
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = repository.findAll();
        // В реальном сервисе здесь может быть какая-то обработка
        // данных, которые пришли из базы, например фильтрация
        // К примеру ми можем отфильтровать автомобили которые пока не
        // готовы к продаже, например, находяться в ремонте
        return  cars;
    }

    @Override
    public Car getById(Long id) {
        Car car = repository.findById(id);

        if (car == null) {
            // Здесь при необходимости тоже может выбрасываться ошибка
        }

        return car;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
