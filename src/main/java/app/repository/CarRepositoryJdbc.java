package app.repository;

import app.constants.Constants;
import app.domain.Car;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.constants.Constants.*;

public class CarRepositoryJdbc implements CarRepository {

    /// ми прописуємо доп метод який відповідає за підключення до бази
    /// і в наступних методах(save, findAll....) будемо його перевикористовувати
    private Connection getConnection() {
        try {
            // потрібно загрузити драйвер в память компютера
            Class.forName(DB_DRIVER_PATH); // ми зробили статичний імпорт навели на DB_DRIVER_PATH і натиснули option enter
            String dbUrl = DB_URL + DB_NAME;
//            String dbUrl = Constants.DB_URL + Constants.DB_NAME;
            return DriverManager.getConnection(dbUrl, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car save(Car car) {
        String sql = "INSERT INTO car (brand, year, price) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setInt(2, car.getYear());
            preparedStatement.setBigDecimal(3, car.getPrice());

            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    long newId = resultSet.getLong(1);
                    car.setId(newId);
                }
            }

            return car;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> findAll() {
        try (Connection connection = getConnection()) {

            // робимо запрос
            String query = "SELECT * FROM car;";
            // отримуємо обєкт який вміє відправляти запроси SQL в базу і віддасть нам відповідь яку база повернула
            Statement statement = connection.createStatement();
            // результат отриманий із бази
            ResultSet result = statement.executeQuery(query);

            // Ств список автомобілів
            List<Car> cars = new ArrayList<>();
            while (result.next()) {
                // починаємо читати кожну колонку про автомобіля
                Long id = result.getLong("id");
                String brand = result.getString("brand");
                int year = result.getInt("year");
                BigDecimal price = result.getBigDecimal("price");

                // створюємо автомобіль (!!! не забуваємо ствоити конструктор із всіма полями у domain -> Car)
                Car car = new Car(id, brand, year, price);
                cars.add(car);
            }
            return cars;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car findById(Long id) {
        try (Connection connection = getConnection()) {

            String query = "SELECT * FROM car WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            Car car = null;

            if (result.next()) {
                String brand = result.getString("brand");
                int year = result.getInt("year");
                BigDecimal price = result.getBigDecimal("price");
                car = new Car(id, brand, year, price);
            }

            return car;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = getConnection()) {

            String query = "DELETE FROM car WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
