package edu.hw10.Task2;

import edu.hw10.Task2.fibcalculators.FibWithPersist;
import edu.hw10.Task2.fibcalculators.FibWithoutPersist;
import edu.hw10.Task2.interfaces.ExampleInterface;
import edu.hw10.Task2.interfaces.FibCalculatorWithPersist;
import edu.hw10.Task2.cache.CacheProxy;
import edu.hw10.Task2.interfaces.FibCalculatorWithoutPersist;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class CacheProxyTest {
    @Test
    @DisplayName("CacheProxyTest (без файла)")
    public void CacheProxyWithoutFileFibTest() {
        FibCalculatorWithoutPersist proxy = new FibWithoutPersist();
        proxy = CacheProxy.create(proxy);

        proxy.fib(20);
        long cachedNum = proxy.fib(20);

        Assertions.assertThat(cachedNum).isEqualTo(6765);
    }

    @Test
    @DisplayName("CacheProxyTest (с файлом)")
    public void CacheProxyWithFileFibTest(@TempDir File tempDir) {
        FibCalculatorWithPersist proxy = new FibWithPersist();
        proxy = CacheProxy.create(proxy, tempDir.toPath());

        long number = proxy.fib(20);
        long cachedNumber = proxy.fib(20);
        Properties properties = new Properties();
        try {
            properties.load(Files.newBufferedReader(tempDir.toPath().resolve("fib_long")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertThat(cachedNumber).isEqualTo(6765);
        Assertions.assertThat(properties.getProperty("20_")).isEqualTo(String.valueOf(number));
    }

    @Test
    @DisplayName("CacheProxyTest (другой интерфейс)")
    public void CacheProxyWithFileExampleTest(@TempDir File tempDir) {
        ExampleInterface proxy = new ExampleImplementation();
        proxy = CacheProxy.create(proxy, tempDir.toPath());

        proxy.getValue();
        long cachedNumber = proxy.getValue();

        Assertions.assertThat(cachedNumber).isEqualTo(69);
        try {
            Assertions.assertThat(Files.readString(tempDir.toPath().resolve("getValue")))
                .isEqualTo("69");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
