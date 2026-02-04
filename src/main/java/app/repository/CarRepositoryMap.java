package app.repository;

import app.domain.Car;

import java.math.BigDecimal;
import java.util.*;

public class CarRepositoryMap implements CarRepository {

    // Имитация базы данных
    private final Map<Long, Car> database = new HashMap<>();
    // Поле которое учитывает какой идентификатор сейчас в БД максимальный
    private long maxId;

    public CarRepositoryMap() {
        save(new Car("Volkswagen", 2010, new BigDecimal(10000)));
        save(new Car("Mazda", 2015, new BigDecimal(20000)));
        save(new Car("Honda", 2020, new BigDecimal(30000)));
    }

    // меотод зберігає в базі даних
    @Override
    public Car save(Car car) {
        car.setId(++maxId);
        database.put(maxId, car); // добавляємо в базу даних
        return car;
    }

    // метод возврата листа всіх автомобілів
    @Override
    public List<Car> findAll() {
        return new ArrayList<>(database.values()); // робимо запрос на список автомобілів і ств додатковий список куди і записуємо

        //варіант 2
//        Collection<Car> cars = database.values();
//        return new ArrayList<>(cars);
    }

    // по ключу отримуємо значення
    @Override
    public Car findById(Long id) {
        return database.get(id);
    }


    // видаляємо по id
    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }
}
