package app.constants;

public interface Constants {

    /// через який драйвер буде підключетися база
    String DB_DRIVER_PATH = "org.postgresal.Driver";
    /// Де лежить база
    String DB_URL = "jdbc:postgresql://localhost:5432/";
    /// Назва бази
    String DB_NAME = "g_74_cars";
    /*
    В реальных проектах секреты никогда не пишится в коде, потому что
    таким образом они станут общедоступными(при выгрузке на Github)
    Секреты хранятся в переменных окружения
     */
    String DB_USERNAME = "postgres";
    String DB_PASSWORD = "qwerty007";
}
