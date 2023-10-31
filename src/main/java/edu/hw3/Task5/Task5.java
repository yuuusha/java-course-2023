package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Task5 {

    private Task5() {

    }

    private static final Comparator<Contact> ASC = Comparator
        .comparing(Contact::surname)
        .thenComparing(Contact::name);

    private static final Comparator<Contact> DESC = ASC.reversed();

    public static List<Contact> parseContacts(String[] names, String typeSort) {
        List<Contact> resultList = new ArrayList<>();

        if (names != null) {

            for (String s : names) {
                String[] nameAndSurname = s.split(" ");
                if (nameAndSurname.length != 2) {
                    throw new RuntimeException("Неверно записаны имя и фамилия");
                }
                resultList.add(new Contact(nameAndSurname[0], nameAndSurname[1]));
            }

            if (typeSort.equals("ASC")) {
                resultList.sort(ASC);
            } else if (typeSort.equals("DESC")) {
                resultList.sort(DESC);
            } else {
                throw new RuntimeException("Неверно указан порядок сортировки");
            }
        }

        return resultList;
    }
}
