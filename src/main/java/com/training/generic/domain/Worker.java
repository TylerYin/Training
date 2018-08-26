package com.training.generic.domain;

/**
 * @Description Worker entity
 * @Author Tyler Yin
 * @Create 2017-11-12 14:57
 **/
public class Worker extends Person{
    public Worker() {
        super();
    }

    public Worker(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Worker [" + "name = " + this.getName() + ", age = " + this.getAge() + "]";
    }
}
