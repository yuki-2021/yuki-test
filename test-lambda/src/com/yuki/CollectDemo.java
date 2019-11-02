package com.yuki;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectDemo {

    public static void main(String[] args) {

        //ArrayList转LinkedHashSet
        ArrayList<Student> students = new ArrayList<>();
        students.stream().filter(student -> student.getScore()>90)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        //ArrayList转Map
        Map<String, Student> collect = students.stream()
                .collect(Collectors.toMap(Student::getName, t -> t));

        //HashMap
        Map<String, Integer> collect1 = Stream.<String>of("a", "b", "c")
                .collect(Collectors.toMap(Function.identity(), String::length,
                        (integer, integer2) -> integer));


        String collect2 = Stream.of("s", "d", "c")
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println(collect2);
    }
}
