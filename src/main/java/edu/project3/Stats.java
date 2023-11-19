package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Stats {
    final List<String> listOfFiles;
    final OffsetDateTime fromDate;
    final OffsetDateTime toDate;
    final int countOfRequest;
    final double averageSizeOfBytes;
    Map<String, Integer> sources;
    Map<Integer, Integer> countOfResponses;

    //Бонусные статистики
    Map<String, Integer> countOfRequestsType;

    List<Map.Entry<String, Integer>> countOfIP;

    private static final String FROM = "--from";
    private static final String TO = "--to";
    private static final int MAX_COUNT = 5;

    final static Map<Integer, String> RESPONSE_CODE = Map.ofEntries(
        Map.entry(200, "Ok"),
        Map.entry(206, "Partial Content"),
        Map.entry(304, "Not Modified"),
        Map.entry(403, "Forbidden"),
        Map.entry(404, "Not Found"),
        Map.entry(416, "Request Range Not Satisfiable"),
        Map.entry(500, "Internal Server Error")
    );

    public Stats(Stream<LogRecord> logRecordStream, Params parameters) {
        List<LogRecord> listOfLogRecords = logRecordStream.sorted().toList();
        listOfFiles = parameters.getListOfPaths();

        if (!parameters.getOtherParams().containsKey(FROM)) {
            fromDate = listOfLogRecords.get(0).getTime();
        } else {
            fromDate = OffsetDateTime.parse(
                parameters.getOtherParams().getOrDefault(FROM, "-") + "T00:00:00+00:00",
                DateTimeFormatter.ISO_OFFSET_DATE_TIME
            );
        }

        if (!parameters.getOtherParams().containsKey(TO)) {
            toDate = listOfLogRecords.get(listOfLogRecords.size() - 1).getTime();
        } else {
            toDate = OffsetDateTime.parse(
                parameters.getOtherParams().getOrDefault(TO, "-") + "T23:59:59+00:00",
                DateTimeFormatter.ISO_OFFSET_DATE_TIME
            );
        }

        listOfLogRecords = listOfLogRecords.stream()
            .filter(logRecord -> logRecord.getTime().isAfter(fromDate)
                && logRecord.getTime().isBefore(toDate)).toList();

        countOfRequest = listOfLogRecords.size();
        long sumOfBytes = 0;
        sources = new HashMap<>();
        countOfResponses = new HashMap<>();
        countOfRequestsType = new HashMap<>();


        TreeMap<String, Integer> countOfIPunsorted = new TreeMap<>();
        String resource;
        int response;
        String requestType;
        String ip;
        for (LogRecord logRecord : listOfLogRecords) {
            sumOfBytes += logRecord.getBytesSent();

            resource = logRecord.getRequest().getSource();
            sources.put(resource, sources.getOrDefault(resource, 0) + 1);

            response = logRecord.getStatusCode();
            countOfResponses.put(response, countOfResponses.getOrDefault(response, 0) + 1);

            requestType = logRecord.getRequest().getRequestType();
            countOfRequestsType.put(requestType, countOfRequestsType.getOrDefault(requestType, 0) + 1);

            ip = logRecord.getRemoteAddress();
            countOfIPunsorted.put(ip, countOfIPunsorted.getOrDefault(ip, 0) + 1);
        }
        averageSizeOfBytes = (double) sumOfBytes / countOfRequest;

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(countOfIPunsorted.entrySet());
        sortedEntries.sort(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
        countOfIP = sortedEntries.subList(0, Math.min(MAX_COUNT, sortedEntries.size()));

    }
}
