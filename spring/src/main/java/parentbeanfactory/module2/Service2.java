package parentbeanfactory.module2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import parentbeanfactory.module1.Service3;

@Component
public class Service2 {
    @Autowired
    Service3 service3;

    @Autowired
    parentbeanfactory.module2.Service1 service1;

    public void medthod() {
        service1.hello();
        service3.hello();
    }
}
