package edu.hw8.Task1;

import java.util.List;

public final class QuoteBank {

    private QuoteBank() {

    }

    private static final List<String> QUOTE_BANK = List.of("Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма",
        "Чем ниже интеллект, тем громче оскорбления"
        );

    public static String get(String keyword) {

        for (String quote : QUOTE_BANK) {
            if (quote.contains(keyword)) {
                return quote;
            }
        }

        return "Цитата не найдена";
    }
}
