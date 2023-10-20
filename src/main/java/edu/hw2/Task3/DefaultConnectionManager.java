package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {

    @Override
    public Connection getConnection() {
        if (RandomSeed.random.nextInt(2) == 0) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
