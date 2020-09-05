package cycledependence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {

    @Autowired
    public B b;

    public A() {
        System.out.println("A init....");
    }
}
