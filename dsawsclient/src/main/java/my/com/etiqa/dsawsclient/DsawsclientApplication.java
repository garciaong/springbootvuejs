package my.com.etiqa.dsawsclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching//Enables Spring Caching functionality
public class DsawsclientApplication {

	private static final Logger log = LoggerFactory.getLogger(DsawsclientApplication.class);
	
	public static void main(String[] args) {
		log.info("Initializing Spring Boot");
		SpringApplication.run(DsawsclientApplication.class, args);
	}

}
