package hdli.register;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegisterServerApplication {

	public static void main(String[] args) {

		 // SpringApplication.run(EurekaServerApplication.class, args);

		new SpringApplicationBuilder(RegisterServerApplication.class).web(true).run(args);
	}
}
