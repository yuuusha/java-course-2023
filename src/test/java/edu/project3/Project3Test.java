package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Project3Test {

    @Test
    @DisplayName("Markdown вывод")
    void markdownTest() {
        String[] args = {"--path", "src/main/java/edu/project3/logs/logs.txt"};
        Params params = new Params(args);
        Stream<LogRecord> logRecordStream = StreamManager.createStream(params);
        Stats stats = new Stats(logRecordStream, params);
        String result = FileCreator.toMarkdown(stats);

        String expectedResult = """
            #### Общая информация

            |Метрика|Значение|
            |:-:|-:|
            |Файл(-ы)| `logs.txt` |
            |Начальная дата| 17.05.2015 |
            |Конечная дата| 18.05.2015 |
            |Количество запросов| 78 |
            |Средний размер ответа| 273.97435897435895 |

            #### Запрашиваемые ресурсы

            |Ресурс|Количество|
            |:-:|-:|
            |`/downloads/product_1`| 46 |
            |`/downloads/product_2`| 32 |

            #### Коды ответа

            |Код|Имя|Количество|
            |:-:|:-:|-:|
            | 304 | Not Modified | 25 |
            | 404 | Not Found | 48 |
            | 200 | Ok | 5 |

            #### Типы запросов

            |Тип запроса|Количество|
            |:-:|-:|
            | GET | 78 |

            #### Первые пять IP, с которых было сделано наибольшее число запросов

            |IP|Количество|
            |:-:|-:|
            | 78.109.87.141 | 14 |
            | 216.46.173.126 | 10 |
            | 188.165.150.136 | 7 |
            | 137.117.183.81 | 6 |
            | 93.180.71.3 | 6 |
            """;
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Adoc вывод")
    void adocTest() {
        String[] args = {"--path", "src/main/java/edu/project3/logs/logs.txt"};
        Params params = new Params(args);
        Stream<LogRecord> logRecordStream = StreamManager.createStream(params);
        Stats stats = new Stats(logRecordStream, params);
        String result = FileCreator.toAdoc(stats);

        String expectedResult = """
            ==== Общая информация

            |===
            |Метрика|Значение
            |Файл(-ы)| `logs.txt`\s
            |Начальная дата| 17.05.2015\s
            |Конечная дата| 18.05.2015\s
            |Количество запросов| 78\s
            |Средний размер ответа| 273.97435897435895\s
            |===
            ==== Запрашиваемые ресурсы

            |===
            |Ресурс|Количество
            |`/downloads/product_1`| 46\s
            |`/downloads/product_2`| 32\s
            |===
            ==== Коды ответа

            |===
            |Код|Имя|Количество
            | 304 | Not Modified | 25\s
            | 404 | Not Found | 48\s
            | 200 | Ok | 5\s
            |===
            ==== Типы запросов

            |===
            |Тип|Количество
            | GET | 78\s
            |===
            ==== Первые пять IP, с которых было сделано наибольшее число запросов

            |===
            |IP|Количество
            | 78.109.87.141 | 14\s
            | 216.46.173.126 | 10\s
            | 188.165.150.136 | 7\s
            | 137.117.183.81 | 6\s
            | 93.180.71.3 | 6\s
            |===
            """;

        assertEquals(expectedResult, result);
    }
}
