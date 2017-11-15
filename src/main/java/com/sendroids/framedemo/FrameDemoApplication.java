package com.sendroids.framedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@Configuration
@RestController
@SpringBootApplication
@EnableWebSecurity //启用web安全
public class FrameDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrameDemoApplication.class, args);
	}
}
