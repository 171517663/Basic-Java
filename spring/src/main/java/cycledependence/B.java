package cycledependence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {
    @Autowired
    public A a;

    public B() {
        System.out.println("init b...");
    }

    
}
