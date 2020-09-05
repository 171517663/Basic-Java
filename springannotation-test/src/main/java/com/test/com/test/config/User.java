package com.test.com.test.config;

import org.springframework.beans.factory.annotation.Value;

public class User {
    String name;
    String sex;
    Pet pet;

    public User(String name, String sex, Pet pet) {
        this.name = name;
        this.sex = sex;
        this.pet = pet;
    }

    public User(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getSex() {
        return sex;
    }

    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Value("nihao")
    public User setName(String name) {
        this.name = name;
        return this;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", pet=" + pet +
                '}';
    }
}
