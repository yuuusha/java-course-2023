package edu.project3;

import java.util.stream.Stream;

public final class LogAnalyzer {

    private LogAnalyzer() {

    }

    public static void logAnalyzerConveyor(String[] args) {
        Params params = new Params(args);
        Stream<LogRecord> logRecordStream = StreamManager.createStream(params);
        Stats stats = new Stats(logRecordStream, params);
        FileCreator.fileCreate(stats, params);
    }

}
