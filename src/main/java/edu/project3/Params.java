package edu.project3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Params {
    private final List<String> listOfPaths;

    private String from;

    private String to;

    private String format;

    private final static String FROM = "--from";
    private final static String TO = "--to";
    private final static String FORMAT = "--format";

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getFormat() {
        return format;
    }

    public List<String> getListOfPaths() {
        return listOfPaths;
    }

    // строгий порядок: --path (несколько элементов) --from (один элемент) --to (один элемент) --format
    public Params(String[] args) {

        List<String> mayBeParams = List.of(FROM, TO, FORMAT);

        try {
            listOfPaths = new ArrayList<>();

            if (!Objects.equals(args[0], "--path")) {
                throw new RuntimeException("Неправильный формат параметров");
            }

            int i = 1;
            while (i < args.length && !mayBeParams.contains(args[i])) {
                listOfPaths.add(args[i]);
                i++;
            }

            while (i < args.length) {
                switch (args[i]) {
                    case FROM: {
                        from = args[++i];
                        break;
                    }
                    case TO: {
                        to = args[++i];
                        break;
                    }
                    case FORMAT: {
                        format = args[++i];
                        break;
                    }
                    default: {
                        break;
                    }
                }
                i++;
            }
        } catch (Exception e) {
            throw new RuntimeException("Неверный формат входных параметров");
        }
    }
}
