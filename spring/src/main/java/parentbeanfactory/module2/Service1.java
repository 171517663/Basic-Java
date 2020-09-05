package parentbeanfactory.module2;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Service1 {
    public void hello() {
        System.out.println("===module2=====");
    }

    @Override
    public String toString() {
        return "Service1{}" + this.getClass().getName();
    }
}
