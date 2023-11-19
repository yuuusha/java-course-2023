package edu.project3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Params {
    private final List<String> listOfPaths;
    private final Map<String, String> otherParams;

    private final static String FROM = "--from";
    private final static String TO = "--to";
    private final static String FORMAT = "--format";

    public List<String> getListOfPaths() {
        return listOfPaths;
    }

    public Map<String, String> getOtherParams() {
        return otherParams;
    }

    // строгий порядок: --path (несколько элементов) --from (один элемент) --to (один элемент) --format
    public Params(String[] args) {

        List<String> mayBeParams = List.of(FROM, TO, FORMAT);

        try {
            listOfPaths = new ArrayList<>();
            otherParams = new HashMap<>();

            int i = 1;
            while (i < args.length && !mayBeParams.contains(args[i])) {
                listOfPaths.add(args[i]);
                i++;
            }

            while (i < args.length) {
                switch (args[i]) {
                    case FROM -> otherParams.put(FROM, args[++i]);
                    case TO -> otherParams.put(TO, args[++i]);
                    case FORMAT -> otherParams.put(FORMAT, args[++i]);
                    default -> {
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
