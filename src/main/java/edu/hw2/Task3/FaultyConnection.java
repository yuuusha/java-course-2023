package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.info("Соединение открыто");

        if (RandomSeed.random.nextInt(2) == 0) {
            throw new ConnectionException("Проблемное соединение");
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Соединение закрыто");
    }
}
