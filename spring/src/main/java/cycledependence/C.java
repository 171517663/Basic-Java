package cycledependence;

import org.springframework.stereotype.Component;

@Component
public class C {
    public C() {
        System.out.println("C init....");
    }
}
