package parentbeanfactory.module1;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Service1 {
    public void hello() {
        System.out.println("===module1=====");
    }

    @Override
    public String toString() {
        return "Service1{}" + this.getClass().getName();
    }
}
