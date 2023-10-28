package edu.hw2;

import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(), 2);
        executor.updatePackages();
    }
}
