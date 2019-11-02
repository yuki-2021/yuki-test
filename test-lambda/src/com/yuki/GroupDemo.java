package com.yuki;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class GroupDemo {

    public static void main(String[] args) {

        List<Student> students = Arrays.asList(new Student[] {
                new Student("zhangsan", "1", 91d),
                new Student("lisi", "2", 89d),
                new Student("wangwu", "1", 50d),
                new Student("zhaoliu", "2", 78d),
                new Student("sunqi", "1", 59d)});

        //分组
        Map<String, List<Student>> collect = students.stream()
                .collect(groupingBy(Student::getGrade));

        //分组统计
        Map<String, Long> collect1 = students.stream().collect(groupingBy(Student::getGrade, counting()));

        //排出顺序
        LinkedHashMap<String, Long> collect2 = Stream.of("hello", "world", "abc", "hello").collect(
                groupingBy(Function.identity(), LinkedHashMap::new, counting()));

        //最高分
        Map<String, Optional<Student>> topStudentMap = students.stream().collect(
                groupingBy(Student::getGrade,
                        maxBy(Comparator.comparing(Student::getScore))));

        //最高分
        Map<String, Student> topStudentMap1 = students.stream().collect(
                groupingBy(Student::getGrade, collectingAndThen(
                        maxBy(Comparator.comparing(Student::getScore)), Optional::get)));
    }
}
