package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamApiTasks_3 {
    private static final List<Integer> numberList = new ArrayList<>();

    static {
        for (int i = 0; i < 1_000_000; i++) {
            numberList.add(i);
        }

        for (int i = 0; i < 50; i++) {
            numberList.add(new Random().nextInt(1_000_000));
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Integer> duplicateNumbersList = getDuplicateElements(numberList);
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
        System.out.println(duplicateNumbersList);
    }

    private static List<Integer> getDuplicateElements (List<Integer> numbers) {
        // 1-й способ
        // 70 ms (1 мл эелемнтов, 50 дубликатов)
        HashSet<Integer> numbersSet = new HashSet<>();
        return numbers.stream()
                .filter(number -> !numbersSet.add(number))
                .collect(Collectors.toList());

        // 2-й способ
        // 110 ms (1 мл эелемнтов, 50 дубликатов)
//        return numbers.stream()
//                .collect(
//                        Collectors.groupingBy(
//                                Function.identity(),
//                                Collectors.counting()))
//                .entrySet().stream()
//                .filter(entry -> entry.getValue() > 1)
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());

        // 3-й способ
        // ооооочень долго. (100 тыс эелемнтов, 50 дубликатов)
//        return numbers.stream()
//                .filter(number -> Collections.frequency(numbers, number) > 1)
//                .collect(Collectors.toSet());
    }
}
