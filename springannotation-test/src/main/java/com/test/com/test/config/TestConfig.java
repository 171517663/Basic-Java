package com.test.com.test.config;
/**
 * @Configuration
 * public class AppConfig {
 *     @Bean
 *     public MyService myService() {
 *         return new MyServiceImpl();
 *     }
 * }
 * The preceding AppConfig class is equivalent to the following Spring <beans/> XML:
 * <beans>
 *     <bean id="myService" class="com.acme.services.MyServiceImpl"/>
 * </beans>
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration//声明这个类是一个配置类
@ComponentScan
@Import(TestConfig2.class)
public class TestConfig {
    @Bean
    User togetUser() {
        return new User();
    }

}
