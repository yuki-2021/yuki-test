package com.yuki;

public class Student {
    private String name;
    private double score;
    private String grade;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student(String name, String grade, double score) {
        this.name = name;
        this.score = score;
        this.grade = grade;
    }
}
