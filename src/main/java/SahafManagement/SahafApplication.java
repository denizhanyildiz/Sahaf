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
		//ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SahafApplication.class, args);

		//IUserRepository iUserRepository = configurableApplicationContext.getBean(IUserRepository.class);
//
		//User user = new User("asd");
//
		//Book book = new Book("makara",user);
		//Book book2 = new Book("makara2",user);
		//List<Book> books = Arrays.asList(book,book2);
		//user.setUserBook(books);
		//iUserRepository.save(user);


	}

}
