package com.training.generic.domain;

/**
 * @Description Student entity
 * @Author Tyler Yin
 * @Create 2017-11-12 14:56
 **/
public class Student extends Person{
    public Student() {
        super();
    }

    public Student(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Student [" + "name = " + this.getName() + ", age = " + this.getAge() + "]";
    }
}
