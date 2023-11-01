package edu.hw3;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Task4 {

    private static final LinkedHashMap<Integer, String> INT_TO_ROMAN = new LinkedHashMap<>();

    private Task4() {

    }

    @SuppressWarnings("MagicNumber")
    private static void setIntToRoman() {
        INT_TO_ROMAN.put(1000, "M");
        INT_TO_ROMAN.put(900, "CM");
        INT_TO_ROMAN.put(500, "D");
        INT_TO_ROMAN.put(400, "CD");
        INT_TO_ROMAN.put(100, "C");
        INT_TO_ROMAN.put(90, "XC");
        INT_TO_ROMAN.put(50, "L");
        INT_TO_ROMAN.put(40, "XL");
        INT_TO_ROMAN.put(10, "X");
        INT_TO_ROMAN.put(9, "IX");
        INT_TO_ROMAN.put(5, "V");
        INT_TO_ROMAN.put(4, "IV");
        INT_TO_ROMAN.put(1, "I");
    }

    @SuppressWarnings("MagicNumber")
    public static String convertToRoman(int number) {

        if (!(number > 0 && number < 4000)) {
            throw new RuntimeException("Некорректный ввод: число вне диапазона от 1 до 3999");
        }

        setIntToRoman();
        int workNumber = number;
        StringBuilder result = new StringBuilder();

        Iterator<Map.Entry<Integer, String>> iterator = INT_TO_ROMAN.entrySet().iterator();
        int cur = iterator.next().getKey();
        while (workNumber > 0) {
            if (workNumber < cur) {
                cur = iterator.next().getKey();
            } else {
                result.append(INT_TO_ROMAN.get(cur));
                workNumber -= cur;
            }
        }

        return result.toString();
    }

}
