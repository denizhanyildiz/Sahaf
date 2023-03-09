package SahafManagement.Component;

import SahafManagement.Repository.ILogRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
Appconfig AOP proxy'leri desteği için kullanılır.
LogAspect sınıfının, @RestController ile açıklama eklenmiş sınıflara yapılan yöntem çağrılarını engelleyerek
 request ve responselar ile ilgili bilgileri günlüğe kaydeden bir tavsiyesi vardır.
 */

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
    @Bean
    public LogAspect logAspect(ILogRepository iLogRepository) {
        return new LogAspect(iLogRepository);
    }
}
