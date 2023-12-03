package edu.project3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Collectors;

public final class FileCreator {

    private static final String HTTP_REGEX = "^https{0,1}:\\/\\/\\S+$";
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String TABLE_SEPARATOR = "|===";

    private static final String MARKDOWN_SEPARATOR = "| %s | %d |\n";
    private static final String ADOC_SEPARATOR = "| %s | %d \n";
    private static final String UNKNOWN = "Unknown";

    private FileCreator() {

    }

    public static void fileCreate(Stats stats, Params params) {
        if (Objects.equals(params.getFormat(), "adoc")) {
            createAdoc(stats);
        } else {
            createMarkdown(stats);
        }
    }

    private static void createMarkdown(Stats stats) {
        String text = toMarkdown(stats);
        Path filePath = Paths.get("src/main/resources/project3/reports/log_report.md");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toMarkdown(Stats stats) {
        StringBuilder result = new StringBuilder();
        result.append("""
            #### Общая информация

            |Метрика|Значение|
            |:-:|-:|
            """);
        String filesName = stats.listOfFiles.stream().map(elem -> {
            if (elem.matches(HTTP_REGEX)) {
                return '`' + elem.substring(elem.lastIndexOf("/")) + '`';
            } else {
                return '`' + Paths.get(elem).getFileName().toString() + '`';
            }
        }).collect(Collectors.joining("; "));
        result.append(String.format("|Файл(-ы)| %s |\n", filesName));
        result.append(String.format(
            "|Начальная дата| %s |\n",
            stats.fromDate.toLocalDateTime().format(DateTimeFormatter.ofPattern(
                DATE_FORMAT))
        ));
        result.append(String.format(
            "|Конечная дата| %s |\n",
            stats.toDate.toLocalDateTime().format(DateTimeFormatter.ofPattern(
                DATE_FORMAT))
        ));
        result.append(String.format("|Количество запросов| %d |\n", stats.countOfRequest));
        result.append(String.format("|Средний размер ответа| %s |\n", stats.averageSizeOfBytes));

        result.append("""

            #### Запрашиваемые ресурсы

            |Ресурс|Количество|
            |:-:|-:|
            """);

        for (var entry : stats.sources.entrySet()) {
            result.append(String.format("|`%s`| %s |\n", entry.getKey(), entry.getValue()));
        }

        result.append("""

            #### Коды ответа

            |Код|Имя|Количество|
            |:-:|:-:|-:|
            """);

        for (var entry : stats.countOfResponses.entrySet()) {
            result.append(String.format(
                "| %d | %s | %d |\n",
                entry.getKey(),
                Stats.RESPONSE_CODE.getOrDefault(entry.getKey(), UNKNOWN),
                entry.getValue()
            ));
        }

        result.append("""

            #### Типы запросов

            |Тип запроса|Количество|
            |:-:|-:|
            """);

        for (var entry : stats.countOfRequestsType.entrySet()) {
            result.append(String.format(
                MARKDOWN_SEPARATOR,
                entry.getKey(),
                entry.getValue()
            ));
        }

        result.append("""

            #### Первые пять IP, с которых было сделано наибольшее число запросов

            |IP|Количество|
            |:-:|-:|
            """);

        for (var entry : stats.countOfIP) {
            result.append(String.format(
                MARKDOWN_SEPARATOR,
                entry.getKey(),
                entry.getValue()
            ));
        }

        return String.valueOf(result);
    }

    private static void createAdoc(Stats stats) {
        String text = toAdoc(stats);
        Path filePath = Paths.get("src/main/resources/project3/reports/log_report.adoc");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toAdoc(Stats stats) {
        StringBuilder result = new StringBuilder();

        result.append("""
            ==== Общая информация

            |===
            |Метрика|Значение
            """);

        String filesName = stats.listOfFiles.stream().map(elem -> {
            if (elem.matches(HTTP_REGEX)) {
                return '`' + elem.substring(elem.lastIndexOf("/")) + '`';
            } else {
                return '`' + Paths.get(elem).getFileName().toString() + '`';
            }
        }).collect(Collectors.joining("; "));

        result.append(String.format("|Файл(-ы)| %s \n", filesName));

        result.append(String.format(
            "|Начальная дата| %s \n",
            stats.fromDate.toLocalDateTime().format(DateTimeFormatter.ofPattern(
                DATE_FORMAT))
        ));
        result.append(String.format(
            "|Конечная дата| %s \n",
            stats.toDate.toLocalDateTime().format(DateTimeFormatter.ofPattern(
                DATE_FORMAT))
        ));
        result.append(String.format("|Количество запросов| %s \n", stats.countOfRequest));
        result.append(String.format("|Средний размер ответа| %s \n", stats.averageSizeOfBytes));
        result.append(TABLE_SEPARATOR);

        result.append("""

            ==== Запрашиваемые ресурсы

            |===
            |Ресурс|Количество
            """);

        for (var entry : stats.sources.entrySet()) {
            result.append(String.format("|`%s`| %s \n", entry.getKey(), entry.getValue()));
        }
        result.append(TABLE_SEPARATOR);

        result.append("""

            ==== Коды ответа

            |===
            |Код|Имя|Количество
            """);

        for (var entry : stats.countOfResponses.entrySet()) {
            result.append(String.format(
                "| %d | %s | %d \n",
                entry.getKey(),
                Stats.RESPONSE_CODE.getOrDefault(entry.getKey(), UNKNOWN),
                entry.getValue()
            ));
        }
        result.append(TABLE_SEPARATOR);

        result.append("""

            ==== Типы запросов

            |===
            |Тип|Количество
            """);

        for (var entry : stats.countOfRequestsType.entrySet()) {
            result.append(String.format(
                ADOC_SEPARATOR,
                entry.getKey(),
                entry.getValue()
            ));
        }
        result.append(TABLE_SEPARATOR);

        result.append("""

            ==== Первые пять IP, с которых было сделано наибольшее число запросов

            |===
            |IP|Количество
            """);

        for (var entry : stats.countOfIP) {
            result.append(String.format(
                ADOC_SEPARATOR,
                entry.getKey(),
                entry.getValue()
            ));
        }
        result.append(TABLE_SEPARATOR).append("\n");

        return String.valueOf(result);
    }
}
