package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {

    @Override
    public Connection getConnection() {
        if (RandomSeed.isAttemptOK(2)) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
