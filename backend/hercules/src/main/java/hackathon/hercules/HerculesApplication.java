package hackathon.hercules;

import hackathon.hercules.entity.StorageProperties;
import hackathon.hercules.repository.StorageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class HerculesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HerculesApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageRepository storage) {
		return (args) -> {
			storage.deleteAll();
			storage.init();
		};
	}

}
