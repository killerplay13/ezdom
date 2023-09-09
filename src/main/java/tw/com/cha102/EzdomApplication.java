package tw.com.cha102;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ServletComponentScan("tw.com.cha102")
@EnableJpaRepositories
public class EzdomApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzdomApplication.class, args);
	}

}
