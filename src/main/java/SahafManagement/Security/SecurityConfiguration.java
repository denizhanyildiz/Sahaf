package SahafManagement.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
/*
userDetailsService metodu kullanıcının kimliğini doğrulamaktan sorumludur.
SecurityFilterChain metodu HttpSecurity nesnesini kullanarak uygulamanın güvenliğini yapılandırır.
crsf yi devre dışı bırakarak tarayıcılar olmadan requestlerin uygulamaya erişimine izin vermek için kullanılır.
requestMatchers ile apilerimizin hangilerinin yetkilendirilip yetkilendirilmeyeceğine karar veririz.
httpBasic ile kullanıcılar isteklerini kullanıcı adı ve şifresi ile istek yapabilmeleri sağlanr.
PasswordEncoder metodu ile kullanıcıların şifresi güvence altına alınır.
AuthenticationProvider metodu ile istek atan kullanıcnın kimliğinin ve şifresinin doğrulanmasından sorumludur,
bu metot userDetailsService ile kullanıcının kimliğini, passwordEncoder ile kullanıcının şifresini doğrular.
DaoAuthenticationProvider yapılandırır.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfiguration{

    @Bean
    //authentication
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/save").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers(
                        "/bookstorepurchase/**"
                        ,"/book/**"
                        ,"/bookstore/**"
                        ,"/bookstorereport/**"
                        ,"/bookstorerent/**"
                        ,"/user/delete/**"
                        ,"/user/update/**")
                .authenticated()
                .and().httpBasic()
                .and().formLogin().permitAll().and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
