package com.zautomate;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class App {
	
	@Autowired
	DataSource dataSource;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
