package com.test.spring;

import java.util.Objects;

public class Hi {
    Hello hello;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hi hi = (Hi) o;
        return Objects.equals(hello, hi.hello);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hello);
    }

    public Hello getHello() {
        return hello;
    }

    public Hi setHello(Hello hello) {
        this.hello = hello;
        return this;
    }
}
