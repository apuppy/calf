package com.demo.calf.data;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CollectionDemo {

    public void ArrayList(){

        /****** ArrayList ******/

        //create ArrayList
        List<String> strList = new ArrayList<>();

        System.out.println("ArrayList initial capacity :"+strList.size());

        //add elements
        strList.add("Hello");
        strList.add("world");
        strList.add(2,"!");
        System.out.println("ArrayList current capacity :"+strList.size());

        //modify ArrayList
        strList.set(0,"HELLO");
        strList.set(1,"WORLD");
        System.out.println("ArrayList current content :"+strList.toString());

        //get element
        String element = strList.get(0);
        System.out.println(element);

        //Iterator
        Iterator<String> iterator = strList.iterator();
        while(iterator.hasNext()){
            String strNext = iterator.next();
            System.out.println(strNext);
        }

        //for
        for(String str : strList){
            System.out.println(str);
        }

        // isEmpty & contains
        boolean isEmpty = strList.isEmpty();
        System.out.println("strList is empty ? " + isEmpty);
        boolean isContain = strList.contains("HELLO");
        System.out.println("strList contains hello ? " + isContain);

        // collection(ArrayList) to Array
        String[] strArray = strList.toArray(new String[]{});
        System.out.println("ArrayList(String) convert to Array(String) : " + strArray.toString());

        //remove element
        strList.remove(0);
        strList.remove("world");
        strList.clear();

        System.out.println("finally strList capacity:" + strList.size());
    }

    public void LinkedList(){
        List<String> linkedList = new LinkedList<>();
        System.out.println("LinkedList initial capacity : "+linkedList.size());

        // add element
        linkedList.add("My");
        linkedList.add("name");
        linkedList.add("is");
        linkedList.add("kevin");
        System.out.println("LinkedList current capacity : "+linkedList.size());

        // modify element
        linkedList.set(0,"my");
        linkedList.set(1,"nickname");
        System.out.println("LinkedList current content : "+linkedList.toString());

        // get element
        String element = linkedList.get(0);
        System.out.println("the first element : "+element);

        // iterator
        Iterator<String> iterator = linkedList.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }

        // for
        for(String str : linkedList){
            System.out.println(str);
        }

        // isEmpty & contains
        boolean isEmpty = linkedList.isEmpty();
        boolean isContains = linkedList.contains("kevin");
        System.out.println(isEmpty);
        System.out.println(isContains);

        // remove element
        linkedList.remove(0);
        linkedList.remove("is");
        linkedList.clear();
        System.out.println("finally linkedList capacity : "+linkedList.size());
    }

    public String HashSet(){
        Set<String> daysOfWeek = new HashSet<>();

        // add element
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        daysOfWeek.add("Thursday");
        daysOfWeek.add("Friday");
        daysOfWeek.add("Saturday");
        daysOfWeek.add("Sunday");

        System.out.println(daysOfWeek);

        // HashSet from another collection
        List<Integer> numbersDivisibleBy5 = new ArrayList<>();
        numbersDivisibleBy5.add(5);
        numbersDivisibleBy5.add(10);
        numbersDivisibleBy5.add(15);
        numbersDivisibleBy5.add(20);
        numbersDivisibleBy5.add(25);

        List<Integer> numbersDivisibleBy3 = new ArrayList<>();
        numbersDivisibleBy3.add(3);
        numbersDivisibleBy3.add(6);
        numbersDivisibleBy3.add(9);
        numbersDivisibleBy3.add(12);
        numbersDivisibleBy3.add(15);

        Set<Integer> numbersDivisibleBy5Or3 = new HashSet<>(numbersDivisibleBy5);
        numbersDivisibleBy5Or3.addAll(numbersDivisibleBy3);

        System.out.println(numbersDivisibleBy5Or3);

        // HashSet operations
        Set<String> popularCities = new HashSet<>();
        System.out.println("Is popularCities set empty ? " + popularCities.isEmpty());

        popularCities.add("London");
        popularCities.add("New York");
        popularCities.add("Paris");
        popularCities.add("Dubai");
        // get the size of a HashSet
        System.out.println("Number of cities in the HashSet : " + popularCities.size());

        // Check if the HashSet contains an element
        String cityName = "Paris";
        if(popularCities.contains(cityName)){
            System.out.println(cityName + " is in the popular cities set.");
        } else {
            System.out.println(cityName + " is not in the popular cities set.");
        }

        // remove element
        Set<Integer> numbers = new HashSet<>();
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);
        numbers.add(9);
        numbers.add(10);

        System.out.println("numbers : " + numbers);
        // Remove an element
        boolean isRemoved = numbers.remove(10);
        System.out.println("After remove(10) => " + numbers);
        // Remove all elements belonging to an given collection from a HashSet
        List<Integer> removeList = new ArrayList<>();
        removeList.add(4);
        removeList.add(9);
        numbers.removeAll(removeList);
        System.out.println("After remove(removeList) : " + numbers);
        //Remove all elements matching a given predicate
        numbers.removeIf(num -> num % 2 == 0);
        System.out.println("After removeIf() => " + numbers);
        //Remove all elements from HashSet (clear it completely)
        numbers.clear();
        System.out.println("After clear() => " + numbers);

        // iterating over a HashSet
        Set<String> programmingLanguages = new HashSet<>();
        programmingLanguages.add("C");
        programmingLanguages.add("C++");
        programmingLanguages.add("Java");
        programmingLanguages.add("Python");
        programmingLanguages.add("PHP");
        programmingLanguages.add("Ruby");
        // java8 foreach and lambda
        System.out.println("=== Iterate over a HashSet using Java 8 forEach and lambda ===");
        programmingLanguages.forEach(programmingLanguage -> {
            System.out.println(programmingLanguage);
        });
        // iterator
        System.out.println("=== Iterator over a HashSet using iterator() ===");
        Iterator<String> programmingLanguageIterator = programmingLanguages.iterator();
        while(programmingLanguageIterator.hasNext()){
            String programmingLanguage = programmingLanguageIterator.next();
            System.out.println(programmingLanguage);
        }
        // iterator and java 8 forEachRemaining
        System.out.println("=== Iterator over a HashSet using iterator() and java 8 forEachRemaining() method ===");
        programmingLanguageIterator = programmingLanguages.iterator();
        programmingLanguageIterator.forEachRemaining(programmingLanguage -> {
            System.out.println(programmingLanguage);
        });
        // for
        System.out.println("=== Iterator over a HashSet using simple for-each loop ===");
        for(String programmingLanguage : programmingLanguages){
            System.out.println(programmingLanguage);
        }

        return "";
    }

}
