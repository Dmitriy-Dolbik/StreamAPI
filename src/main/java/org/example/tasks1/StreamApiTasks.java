package org.example.tasks1;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Hello world!
 */
public class StreamApiTasks {
    private String source = "https://www.cesar-lp.com/posts/java-streams-exercises";
    private static List<Person> peopleList;
    private static List<List<Person>> listOfPeopleLists;

    static {
        Country france = new Country("France", 65_235_184L);
        Country canada = new Country("Canada", 37_653_095L);
        Country uk = new Country("United Kingdom", 67_791_734L);

        //Список людей
        peopleList = getPersonList(france, canada, uk);

        List<Person> listOfPersonList1 = getPersonList3(france, canada);

        List<Person> listOfPersonList2 = getPersonList3(france, canada, uk);

        //Список списков людей
        listOfPeopleLists = Arrays.asList(
                listOfPersonList1,
                listOfPersonList2);
    }

    public static void main(String[] args) {
        System.out.println(getCanadianMales(peopleList));
    }

    private static List<String> getUppercasePeopleNames(List<Person> peopleList) {
        return peopleList.stream()
                .map(person -> person.getName().toUpperCase())
                .collect(Collectors.toList());
    }

    private static List<String> getUppercasePeopleNamesFofLostOfList(List<List<Person>> listOfPeopleLists) {
        return listOfPeopleLists.stream()
                .flatMap(Collection::stream)
                .map(person -> person.getName().toUpperCase())
                .collect(Collectors.toList());
    }

    private static List<String> getPeopleNamesWithLengthLessThanFour(List<Person> peopleList) {
        return peopleList.stream()
                .map(Person::getName)
                .filter(name -> name.length() < 4)
                .collect(Collectors.toList());
    }

    private static List<String> getPeopleNamesWithLengthLessThanFourListOfList(List<List<Person>> listOfPeopleList) {
        return listOfPeopleList.stream()
                .flatMap(Collection::stream)
                .map(Person::getName)
                .filter(name -> name.length() < 4)
                .collect(Collectors.toList());
    }

    private static List<Person> getFlatSingleCollectionFromNestedPersonCollection (List<List<Person>> listOfPeopleList) {
        return listOfPeopleList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static Integer getOldestPersonAge (List<Person> peopleList) {
        return peopleList.stream()
                .map(Person::getAge)
                .max(Integer::compare)
                .orElse(null);
    }

    private static Integer getOldestPersonAgeListOfList (List<List<Person>> listOfPeopleList) {
        return listOfPeopleList.stream()
                .flatMap(Collection::stream)
                .map(Person::getAge)
                .max(Integer::compare)
                .orElse(null);
    }

    private static Integer getSumOfAllAges (List<Person> peopleList) {
        return peopleList.stream()
                .mapToInt(Person::getAge)
                .reduce(0, Integer::sum);
    }

    private static PeopleSummary getPeopleSummary (List<Person> peopleList) {

        Double averageAge = peopleList.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);

        Integer minAge = peopleList.stream()
                .map(Person::getAge)
                .min(Integer::compare)
                .orElse(null);

        Integer maxAge = peopleList.stream()
                .map(Person::getAge)
                .max(Integer::compare)
                .orElse(null);

        Integer amountOfPeople = peopleList.size();

        return PeopleSummary.builder()
                .averageAge(averageAge)
                .minAge(minAge)
                .maxAge(maxAge)
                .amountOfPeople(amountOfPeople)
                .build();

    }

    private static Map<Boolean, List<Person>> groupByAgeStatus (List<Person> peopleList) {
        return peopleList.stream()
                .collect(Collectors.partitioningBy(Person::isAdult));
    }

    private static Map<Country, List<Person>> groupByCountry (List<Person> peopleList) {
        return peopleList.stream()
                .collect(Collectors.groupingBy(Person::getCountry));
    }

    private static Map<String, List<Person>> groupByCountryString (List<Person> peopleList) {
        return peopleList.stream()
                .collect(Collectors.groupingBy(person -> person.getCountry().getName()));
    }

    private static String getAllPeopleNames (List<Person> personList) {
        return peopleList.stream()
                .map(Person::getName)
                .collect(Collectors.joining("_"));
    }

    private static List<Person> getCanadianMales (List<Person> personList) {
        Predicate<Person> isCanadian = person -> "canada".equalsIgnoreCase(person.getCountry().getName());
        Predicate<Person> isFemale = person -> Gender.FEMALE.equals(person.getGender());
        Predicate<Person> isMale = person -> Gender.MALE.equals(person.getGender());
        return peopleList.stream()
                .filter(isCanadian.and(isFemale.negate()))
                .collect(Collectors.toList());
    }

    private static List<Person> getPersonList3(Country france, Country canada, Country uk) {
        return asList(
                new Person("Tiff", 23, Gender.NON_BINARY, uk),
                new Person("Azul", 15, Gender.FEMALE, france),
                new Person("Samantha", 67, Gender.FEMALE, canada));
    }

    private static List<Person> getPersonList3(Country france, Country canada) {
        return asList(
                new Person("John", 50, Gender.MALE, canada),
                new Person("May", 12, Gender.FEMALE, france));
    }

    private static List<Person> getPersonList(Country france, Country canada, Country uk) {
        return asList(
                new Person("John", 50, Gender.MALE, canada),
                new Person("May", 12, Gender.FEMALE, france),
                new Person("Tiff", 23, Gender.NON_BINARY, uk),
                new Person("Azul", 15, Gender.FEMALE, france),
                new Person("Samantha", 67, Gender.FEMALE, canada));
    }

    @Data
    @Builder
    static class PeopleSummary {
        private Double  averageAge;
        private Integer minAge;
        private Integer maxAge;
        private Integer amountOfPeople;
    }
}
