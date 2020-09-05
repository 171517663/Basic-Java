package initbean;

import org.springframework.context.annotation.Configuration;

@Configuration
public class A {
    static {
        System.out.println("static a============");
    }

    public A() {
        System.out.println("constructor a=========");
    }

}
