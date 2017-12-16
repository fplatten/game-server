package com.culture.api.gameserver;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.culture.api.gameserver.service.EventGenerator;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.culture.api.gameserver"} )
@EnableJpaRepositories(basePackages = {"com.culture.api.gameserver.repository"} )
public class Application implements CommandLineRunner {
	
	@Autowired
	EventGenerator generator;

	public static void main(String[] args) throws Exception {

		// to run from maven: mvn spring-boot:run
		// to create war file for remote deployment: mvn -Pdeploy package
		
		ApplicationContext app = SpringApplication.run(Application.class, args);
		
		
		//SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Future<String> eventGenerator = generator.createEvents();
		
		Thread.currentThread().join();
		
		
	}

}
