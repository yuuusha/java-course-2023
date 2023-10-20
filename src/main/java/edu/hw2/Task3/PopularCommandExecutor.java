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

        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                LOGGER.info("Успешное выполнение");
                return;
            } catch (ConnectionException exception) {
                LOGGER.info(exception.getMessage() + ", повторное подключение...");
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }
        }
        throw new ConnectionException("Соединение прервано, превышено количество попыток");
    }
}
