package edu.hw9;

import edu.hw9.Task1.StatsCollector;
import edu.hw9.Task1.StatsResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    @DisplayName("Сбор статистики")
    void statsCollectorTest() {
        StatsCollector collector = new StatsCollector();

        collector.push("metric_name_1", new double[]{1.0, 2.0, 3.0, 4.0, 5.0});

        List<StatsResult> results = collector.stats();

        for (var result : results) {
            assertEquals("metric_name_1", result.getMetricName());
            assertEquals(15.0, result.getSum());
            assertEquals(3.0, result.getAverage());
            assertEquals(5.0, result.getMax());
            assertEquals(1.0, result.getMin());
        }
    }
}
