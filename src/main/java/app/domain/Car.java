package app.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Car {
    // Пишемо поля
    private Long id;
    private String brand;
    private int year;
    private BigDecimal price;

    // Створюємо конструктор без id
    public Car(String brand, int year, BigDecimal price) {
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    // Прописуємо методи - гетери і сетери
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    // метод для порівнянн класів
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && Objects.equals(id, car.id) && Objects.equals(brand, car.brand) && Objects.equals(price, car.price);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(brand);
        result = 31 * result + year;
        result = 31 * result + Objects.hashCode(price);
        return result;
    }

    // метод переробляє в рядок
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
