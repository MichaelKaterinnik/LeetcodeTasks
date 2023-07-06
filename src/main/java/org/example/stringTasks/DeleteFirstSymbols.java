package org.example.stringTasks;

import java.util.List;

public class DeleteFirstSymbols {

    public static void deleteFirstSymbol(List<String> names) {

        //метод replaceAll изменяет каждый элемент в списке согласно лямбда-функции
        names.replaceAll(s -> s.substring(1));

        names.sort(null);
    }
}
