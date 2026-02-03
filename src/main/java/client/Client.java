package client;

import app.controller.CarController;
import app.domain.Car;

import java.math.BigDecimal;
import java.util.List;

/**
Імітуєм front
 */
public class Client {

    public static void main(String[] args) {
        CarController controller = new CarController();

        // Имитация http-запроса
        Car savedCar = controller.save("Volkswagen", 2010, new BigDecimal(10000));
        System.out.println("Сохраненный автомобиль: ");
        System.out.println(savedCar);

        savedCar = controller.save("Mazda", 2015, new BigDecimal(20000));
        System.out.println("Сохраненный автомобиль: ");
        System.out.println(savedCar);

        System.out.println();

        List<Car> cars = controller.getAll();
        System.out.println("Все автомобили из БД");
        cars.forEach(System.out::println);

        System.out.println("Полученный автомобиль по идентификатору:");
        System.out.println(controller.getById(2L));

        System.out.println();

        // Удаляем автомобиль, а затем запрашиваем весь список
        // (удалённого автомобиля в списке не будет
        controller.deleteById(2L);
        cars = controller.getAll();
        System.out.println("Все автомобили из БД:");
        cars.forEach(System.out::println);
    }
}

/**
 1️⃣ Ядро (домен, предметная область) – содержит описание сущностей, с которыми работает наше приложение.
 2️⃣ Репозиторий – отвечает за работу с базой данных.
 3️⃣ Сервис – содержит бизнес-логику приложения (решает задачи заказчика).
 4️⃣ Контроллер – принимает запросы от пользователя и вызывает сервис.
 */