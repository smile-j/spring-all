package com.dong.demo.jdk8.stream;


import com.dong.demo.entity.Student;
import com.dong.demo.enums.GenderEnum;
import com.dong.demo.enums.Grade;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 收集器
 *
 *
 *
 */
public class CollectionDemo {

    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
                new Student("小红",12, Grade.ONE, GenderEnum.MALE),
                new Student("小橙",13, Grade.FOUT, GenderEnum.FEMALE),
                new Student("小黄",16, Grade.TWO, GenderEnum.MALE),
                new Student("小绿",18, Grade.FOUT, GenderEnum.FEMALE),
                new Student("小青",12, Grade.THREE, GenderEnum.MALE),
                new Student("小蓝",13, Grade.FOUT, GenderEnum.FEMALE),
                new Student("小紫",19, Grade.THREE, GenderEnum.FEMALE),
                new Student("小张",8, Grade.ONE, GenderEnum.MALE),
                new Student("小王",11, Grade.THREE, GenderEnum.FEMALE),
                new Student("小李",23, Grade.TWO, GenderEnum.MALE),
                new Student("小赵",16, Grade.ONE, GenderEnum.MALE),
                new Student("小孙",18, Grade.TWO, GenderEnum.MALE));

        //得到所有学生的年龄的列表
//        List<Integer> collect = students.stream().map(Student::getAge).collect(Collectors.toList());
//        Set<Integer> collect = students.stream().map(Student::getAge).collect(Collectors.toSet());
        Set<Integer> collect = students.stream().map(Student::getAge).collect(Collectors.toCollection(TreeSet::new));
        System.out.println("所有学生的年龄："+collect);
        //统计汇总信息
        IntSummaryStatistics collect1 = students.stream().collect(Collectors.summarizingInt(Student::getAge));
        System.out.println("年龄汇总信息"+collect1);
        //分块
        Map<Boolean, List<Student>> collect2 = students.stream().collect(Collectors.partitioningBy(s -> s.getGender() == GenderEnum.MALE));
        System.out.println("男女学生列表"+collect2);
        //分组
        Map<Grade, List<Student>> collect3 = students.stream().collect(Collectors.groupingBy(Student::getGrade));
        System.out.println("班级分组："+collect3);
        //得到所有班级的个数
        Map<Grade, Long> collect4 = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.counting()));
        System.out.println("学生班级列表："+collect4);

    }
}
