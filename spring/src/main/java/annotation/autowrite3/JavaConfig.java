package annotation.autowrite3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("annotation.autowrite3")
//@Import("配置类")
public class JavaConfig {
    @Bean
    Bird getBird() {
        return new Bird();
    }
}
