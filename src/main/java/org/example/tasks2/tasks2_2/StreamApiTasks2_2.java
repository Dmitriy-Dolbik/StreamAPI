package org.example.tasks2.tasks2_2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApiTasks2_2 {
    private  String source = "https://www.programmersought.com/article/47551151501/";
    private static List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    private static List<Employee> employees = Arrays.asList(
            new Employee(" ", 18, 9999.99),
            new Employee(" ", 58, 5555.55),
            new Employee(" ", 26, 3333.33),
            new Employee(" ", 36, 6666.66),
            new Employee(" ", 12, 8888.88),
            new Employee(" ", 12, 8888.88));

    public static void main(String[] args) {
        System.out.println(getCountOfEmployees(employees));
    }

    public static List<Integer> getSquares(List<Integer> numbers) {
        return numbers.stream()
                .map(numb -> numb * numb)
                .collect(Collectors.toList());
    }

    public static long getCountOfEmployees(List<Employee> employees) {
//        return employees.stream()
//                .count();
//        return employees.size();
        return employees.stream()
                .map(employee -> 1)
                .reduce(0, Integer::sum);
    }
}
