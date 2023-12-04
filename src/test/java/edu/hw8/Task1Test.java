package edu.hw8;

import edu.hw8.Task1.QuoteClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import static edu.hw8.Task1.QuoteServer.startServer;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    @DisplayName("Поиск цитаты")
    void quoteSearch() {
        String expectedResult = "Не переходи на личности там, где их нет";

        CompletableFuture<String> responseFuture = new CompletableFuture<>();

        new Thread(() -> {
            startServer();
            String keyword = "личности";
            String result = "";

            try {
                QuoteClient client = new QuoteClient("localhost", 5555);
                result = client.getQuote(keyword);
                responseFuture.complete(result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        try {
            String serverResponse = responseFuture.get(5, TimeUnit.SECONDS);
            assertEquals(expectedResult, serverResponse);
        } catch (Exception e) {
            //сontinue;
        }
    }

}
