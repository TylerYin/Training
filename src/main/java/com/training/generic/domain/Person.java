package com.training.generic.domain;

/**
 * @Description Person entity
 * @Author Tyler Yin
 * @Create 2017-11-12 14:45
 **/
public class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(){
        super();
    }

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        int temp = this.age - o.age;
        return temp == 0 ? this.name.compareTo(o.name) : temp;
    }

    @Override
    public String toString() {
        return "Person [" + "name = " + name + ", age = " + age + "]";
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


}
