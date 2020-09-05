package lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@ComponentScan
@Configuration
public class AppConfig {

    @Bean(initMethod = "initMedthod", destroyMethod = "destroyMethod")
    A getA() {
        return new A();
    }
}
