package com.training.apache.commons.beanutils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tyler Yin
 */
public class CopiedPerson {
    private String name = "";
    private String email = "";
    private int age;

    private List<Hobby> hobbies = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
}
