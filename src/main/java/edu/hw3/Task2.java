package edu.hw3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class Task2 {

    private Task2() {

    }

    public static List<String> clusterBrackets(String inputString) {
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder resultString = new StringBuilder();
        List<String> resultList = new ArrayList<>();

        for (char ch : inputString.toCharArray()) {
            resultString.append(ch);

            if (ch == '(') {
                stack.addFirst(ch);
            } else if (ch == ')') {
                if (!stack.isEmpty()) {
                    stack.removeFirst();
                } else {
                    throw new RuntimeException("Некорректный ввод: некластеризуемая последовательность");
                }

                if (stack.isEmpty()) {
                    resultList.add(resultString.toString());
                    resultString.setLength(0);
                }
            } else {
                throw new RuntimeException("Некорректный ввод: встретился символ, отличный от \"(\" или \")\"");
            }
        }

        return resultList;
    }
}
