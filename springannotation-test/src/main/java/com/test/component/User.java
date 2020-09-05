package com.test.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//等价于<bean id="user" class="com.test.component.User"/>
@Component
//@Service,@Repository,@Controller功能和@Component一样。
//@Service用于service层，@Repository用于dao层,@Controller用于servlet层
//四个功能都是<bean id="user" class="com.test.component.User"/>  装配bean
public class User {

    @Value("xiaohuang")
    String name;

    String sex = "girl";

    @Value("xiaohuang")
    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
