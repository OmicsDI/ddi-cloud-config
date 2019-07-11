package uk.ac.ebi.ddi.ddicloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class DdiCloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdiCloudConfigApplication.class, args);
	}

}
