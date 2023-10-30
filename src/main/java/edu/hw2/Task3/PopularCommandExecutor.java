package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;
    private final static Logger LOGGER = LogManager.getLogger();

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {

        Connection connection = manager.getConnection();
        LOGGER.info("Соединение открыто");
        int i = 0;
        while (i < maxAttempts) {
            try {
                connection.execute(command);
                LOGGER.info("Успешное выполнение");
                break;
            } catch (ConnectionException exception) {
                LOGGER.info("Повторное подключение:", exception);
            }
            i++;
        }
        try {
            connection.close();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        if (i == maxAttempts) {
            throw new ConnectionException("Соединение прервано, превышено количество попыток");
        }
    }
}
