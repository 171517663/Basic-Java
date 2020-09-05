package com.test.com.test.config;

import org.springframework.context.annotation.Bean;

public class TestConfig2 {
    @Bean
    User getUser() {
        return new User("TestConfig2User","boy");
    }

    @Bean
    User getUserWithPet(Pet pet) {
        return new User("TestConfig2User","boy", pet);
    }
}
