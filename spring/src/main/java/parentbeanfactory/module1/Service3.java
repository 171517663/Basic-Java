package parentbeanfactory.module1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service3 {
    @Autowired
    Service1 service1;
    public void hello(){
        this.service1.hello();
    }
}
