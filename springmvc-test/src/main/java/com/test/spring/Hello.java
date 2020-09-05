package com.test.spring;

public class Hello {
    private String name;

    public String getName() {
        return name;
    }

    public Hello(String name) {
        this.name = name;
    }

    public Hello() {
    }

    public Hello setName(String name) {
        this.name = name;
        return this;
    }
}
