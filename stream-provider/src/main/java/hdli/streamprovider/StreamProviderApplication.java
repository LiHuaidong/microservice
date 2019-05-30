package hdli.streamprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StreamProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamProviderApplication.class, args);
	}

}
