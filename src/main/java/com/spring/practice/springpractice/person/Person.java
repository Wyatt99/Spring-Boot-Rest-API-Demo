package com.spring.practice.springpractice.person;

import java.util.List;

public class Person {
    private String name;
    private int age;
    private List<Job> jobs;

    public Person(String name, int age, List<Job> jobs) {
        this.name = name;
        this.age = age;
        this.jobs = jobs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", jobs=" + jobs +
                '}';
    }
}
