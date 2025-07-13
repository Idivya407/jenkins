package com.cicd;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JenkinsProjectApplication {

	public static void main(String[] args) {
		//SpringApplication.run(JenkinsProjectApplication.class, args);
	    SpringApplication app = new SpringApplication(JenkinsProjectApplication.class);

	     app.setDefaultProperties(Collections.singletonMap("server.port", "9090")); // comment this out
	     app.run(args);

	}

}
