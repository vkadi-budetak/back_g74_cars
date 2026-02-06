package app.repository;

import app.domain.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CarRepositoryHibernate implements CarRepository {

    // звертаємся до EntityManager(це як менеджер в компанії hibernate)
    private final EntityManager entityManager;

    // Ств конструктор, він визивається коли буде ств обєкт
    public CarRepositoryHibernate() {
        // ств обєкт спеціального класу Configuration, тобто тут прописується логіка звернень до файлів і закладає вірні налаштування
        entityManager = new Configuration()
                // прописуємо де лежить наша база
                .configure("postgres.cfg.xml")
                // створюємо дирекотра , цей метод створює і поаертає нам createEntityManager
                .buildSessionFactory()
                .createEntityManager();
    }


    @Override
    public Car save(Car car) {
        // зберігаємо автомобідь
        ////!! Коли є зміни у базі, а це видалення чи добавлення,
        //// ОБОВЯЗКОВО ПОТРІБНО ВИКОРИСТОВУВАТИ ТРАНЗАКЦІЇ
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            // відкриваємо транзакці
            transaction.begin();
            // зберігаємо транзакцію
            entityManager.persist(car);
            // закриваємо транзакцію
            transaction.commit();
            return car;
        } catch (Exception e) {
            // перевіряємо чи активна транзакція і тоді відкатуємо її
            if (transaction.isActive()) {
                // відкатуємо транзакцію
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> findAll() {
        // звертаємся до entityManager
        return entityManager.createQuery("from Car", Car.class).getResultList();
    }

    @Override
    public Car findById(Long id) {
        // звертаємся до entityManager передаємо назву класу і що шукаємо
        return entityManager.find(Car.class, id);
    }

    @Override
    public void deleteById(Long id) {

    }
}
