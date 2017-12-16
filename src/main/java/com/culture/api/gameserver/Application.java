package com.culture.api.gameserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) throws Exception {

		// to run from maven: mvn spring-boot:run
		// to create war file for remote deployment: mvn -Pdeploy package
		SpringApplication.run(Application.class, args);
	}

}
