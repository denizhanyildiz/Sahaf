package SahafManagement.Component;

import SahafManagement.Entity.Log;
import SahafManagement.Entity.User;
import SahafManagement.Repository.ILogRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
restController metodu @RestController anatasyonu eklenmiş tüm metodları belirleyen bir Pointcut tanımlar.
logRestController, restController tarafından belirlenen bir yöntem olduğunda çağırılır ve ona iletilen parametrelerin
 bilgisini almak için ProceedingJoinPoint parametresini kullanır.
HttpServletRequest çalışacak olan request hakında bilgi almak için kullanılır.
responseStatus başarılı olduğunda dönecek durum olan HTTP durum koduna ayarlanır.
Yeni bir log yaratılır içerisine ilgili bilgiler set edilir.
Daha sonra veri tabanına kaydedilir.
 */

@Aspect
@Component
public class LogAspect {

    private final ILogRepository iLogRepository;

    public LogAspect(ILogRepository iLogRepository) {
        this.iLogRepository = iLogRepository;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {}

    @Around("restController()")
    public Object logRestController(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestUrl = request.getRequestURL().toString();
        String requestMethod = request.getMethod();
        //String requestBody = IOUtils.toString(request.getReader());

        Object response = joinPoint.proceed();

        int responseStatus = 200;
        String responseBody = null;
        if (response instanceof ResponseEntity) {
            responseStatus = ((ResponseEntity) response).getStatusCodeValue();
            responseBody = ((ResponseEntity) response).getBody().toString();
        } else {
            responseBody = response.toString();
        }

        Log log = new Log();
        log.setRequestUrl(requestUrl);
        log.setRequestMethod(requestMethod);
        //log.setRequestBody(requestBody);
        log.setResponseStatus(responseStatus);
        log.setResponseBody(responseBody);
        log.setCreatedAt(new Date());
        iLogRepository.save(log);

        return response;
    }
}
