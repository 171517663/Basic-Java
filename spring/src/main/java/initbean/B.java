package initbean;

import org.springframework.context.annotation.Configuration;

@Configuration
public class B {
    static {
        System.out.println("static b============");
    }

    public B() {
        System.out.println("constructor b=========");
    }
}
