package SahafManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = "SahafManagement")
@EnableAutoConfiguration
@EnableWebSecurity
public class SahafApplication {
    public static void main(String[] args) {
        SpringApplication.run(SahafApplication.class, args);
    }
}
