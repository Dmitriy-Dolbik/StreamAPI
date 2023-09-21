package org.example.tasks2.tasks2_1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamApiTasks2_1 {
    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario", "Milan");
    static Trader alan = new Trader("Alan", "Cambridge");
    static Trader brian = new Trader("Brian", "Cambridge");
    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {
        System.out.println(isThereTraderWorkingInMilan(transactions));
    }

    public static List<Transaction> getSortedTransactionsByValue(List<Transaction> transactions, int year) {
        return transactions.stream()
                .filter(transaction -> transaction.getYear() == year)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
    }

    public static List<String> getAllDifferentCities(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Trader> getTradersFromCambridge(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "cambridge".equalsIgnoreCase(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    public static List<String> getAllSortedTradersNames(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static boolean isThereTraderWorkingInMilan(List<Transaction> transactions) {
        return transactions.stream()
                .anyMatch(transaction -> "milan".equalsIgnoreCase(transaction.getTrader().getCity()));
    }


}
