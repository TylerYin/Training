package com.training.reflect;

/**
 * 实体类
 *
 * @author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class Person {

    private Integer id;
    private String name;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public Person(Integer id) {
        this.id = id;
    }

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
